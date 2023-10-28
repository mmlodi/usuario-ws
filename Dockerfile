# Use JDK 17 as base image
FROM openjdk:17

# Metadata as key-value pairs
LABEL maintainer="mischellodi@gmail.com"

# Copy the JAR file to the image
COPY target/usuario-ws-0.0.1.jar app.jar

# Specify entry point
ENTRYPOINT ["java", "-jar", "/app.jar"]
