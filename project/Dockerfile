# TODO: Replace <PROJECT_NAME> below with your project’s name
#       (See Project Explorer in Eclipse)


## Based on https://community.render.com/t/3232

# Build stage
FROM maven:3.8.6-eclipse-temurin-17-focal AS build
COPY src /backend/project/src
COPY pom.xml /backend/project
RUN mvn -f /backend/project/pom.xml clean package

# Package stage
FROM eclipse-temurin:17-jre-focal
COPY --from=build /backend/project-0.0.1-SNAPSHOT.jar /usr/local/lib/pkg.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/pkg.jar"]
