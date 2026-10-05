FROM eclipse-temurin:17-jre-jammy

# Set working directory inside container
WORKDIR /shiva

# Copy the JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar demoshiva.jar

# Expose the application port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "demoshiva.jar"]