FROM openjdk:11

VOLUME /tmp

COPY target/app.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]