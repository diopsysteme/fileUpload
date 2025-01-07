FROM openjdk:21

WORKDIR /app

COPY target/fileUpload-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
