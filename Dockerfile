FROM adoptopenjdk/openjdk11
LABEL authors="dsh"
WORKDIR /app
COPY build/libs/api-0.0.1-SNAPSHOT.jar /app/api.jar
ENTRYPOINT ["java", "-jar", "api.jar"]