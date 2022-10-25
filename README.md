# Spring Boot k8s-demo
[![Java CI with Maven](https://github.com/georgeracu/spring-boot-demo-app/actions/workflows/maven.yaml/badge.svg?branch=main)](https://github.com/georgeracu/spring-boot-demo-app/actions/workflows/maven.yaml)

Demo repository for running a Spring Boot app in a Kubernetes cluster

## How to run it

### For debugging locally

```shell
docker-compose up -f docker/docker-compose.yaml &&
./mvnw clean spring-boot:run
```

### Running in a k8s cluster locally

```shell
docker build -t demo-app:v1 .
docker run -it -p8080:8080 --name=demo-app demo-app:v1
minikube addons enable metrics-server
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
kubectl apply -f horizontal-autoscaler.yaml
```

#### Running `minikube`

```shell
minikube start                            # Start minikube locally
minikube addons enable metrics-server     # Required to collect metrics from pods
minikube addons enable ingress            # Required to enable the NGINX Ingress controller
minikube dashboard                        # Open the dashboard for minikube
```

#### Running `postgres`

```shell
cd infra/postgres   # Need to be in the directory with k8s files for postgres
kubectl apply -f .
```

#### Running `Pact broker`

Running Pact broker has a dependency on having PG running first. Please start PG server first. Pact is required to be running for contract tests.

```shell
cd infra/pact-broker
kubectl apply -f .
kubectl expose deployment pact-broker-deployment --type=NodePort --port=9292
```

After starting Pact broker, make sure that the URL used in Pact tests matches the URL where the Pact broker can be found, otherwise tests will fail.

#### Running PGAdmin

```shell
 docker run -p 7080:80 \
    -e 'PGADMIN_DEFAULT_EMAIL=user@domain.com' \
    -e 'PGADMIN_DEFAULT_PASSWORD=SuperSecret' \
    -d dpage/pgadmin4
```

#### Connecting to PGAdmin when running with docker-compose

- host: hotels-db
- user: postgres
- password: mysecretpassword

## Things to do

* Pact broker
* ArchUnit
* E2E working example of a use-case
* Mapping of objects

## How Pact broker works

Pact broker is used to test that the contracts between consumers and providers are still valid with each web service build.

### Publish pacts to the Pact broker (via UI)

* Make sure that the broker is up and running
* Connect to the UI by getting the URL for the broker from k8s
* Go to API Browser and select "Publish a Pact" with NON-GET
* Change the method type to PUT, update query parameters to match the consumer and provider names and the version for consumer
* Paste JSON pact in the body
* Hit Send

## Multi-module Maven app

Create a Maven parent repository

`mvn archetype:generate -DgroupId=com.georgeracu -DartifactId=demo-app`

Inside the `pom.xml` file that's generated, add the following line

`<packaging>pom</packaging>`

Generate next modules

```shell
cd demo-app
mvn archetype:generate -DgroupId=com.georgeracu -DartifactId=domain
mvn archetype:generate -DgroupId=com.georgeracu -DartifactId=app
mvn archetype:generate -DgroupId=com.georgeracu -DartifactId=persistence
```

Build the project

`mvn clean package`