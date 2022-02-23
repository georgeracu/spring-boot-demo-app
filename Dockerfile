FROM openjdk:17

VOLUME /tmp

COPY target/app.jar app.jar

ENTRYPOINT ["java","-Dspring.profiles.active=production","-jar","/app.jar"]