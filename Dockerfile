FROM maven:3.8.6-jdk-8 AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package -DskipTests

FROM openjdk:8-jre-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/springboot-mongo-docker.jar /app/

ENTRYPOINT ["java", "-jar", "springboot-mongo-docker.jar"]