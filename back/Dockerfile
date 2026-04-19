FROM maven:3.9-eclipse-temurin-17 AS build
RUN mkdir /project
COPY . /project
WORKDIR /project
# Monte le cache Maven pour éviter de tout retélécharger
RUN --mount=type=cache,target=/root/.m2 mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-alpine
RUN mkdir /app
COPY --from=build /project/target/*.jar /app/java-application.jar
WORKDIR /app
CMD ["java", "-jar", "java-application.jar"]