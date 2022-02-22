# sussex-k8s-demo
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

## Running PGAdmin

```shell
 docker run -p 7080:80 \
    -e 'PGADMIN_DEFAULT_EMAIL=user@domain.com' \
    -e 'PGADMIN_DEFAULT_PASSWORD=SuperSecret' \
    -d dpage/pgadmin4
```

## Connecting to PGAdmin when running with docker-compose

- host: hotels-db
- user: postgres
- password: mysecretpassword

## Things to do

* ArchUnit
* E2E working example of a use-case
* Mapping of objects
