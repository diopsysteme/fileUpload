# Step 1: Use an official OpenJDK runtime as a parent image
FROM openjdk:21

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy the executable JAR from the target folder into the container
# Adjust this to match the actual JAR name and path after building with Maven
COPY target/fileUpload-0.0.1-SNAPSHOT.jar /app/app.jar

# Step 4: Expose the port that the Spring Boot app will run on
EXPOSE 8080

# Step 5: Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
