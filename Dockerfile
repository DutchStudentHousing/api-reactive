# build stage
FROM gradle:latest AS BUILD
WORKDIR /app/
# COPY . .
RUN git clone https://github.come/DutchStudentHousing/api-reactive.git --recursive
RUN gradle build

# package stage
FROM adoptopenjdk/openjdk11
LABEL authors="dsh"
WORKDIR /app
COPY --from=BUILD /app/build/libs/* .
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "api-0.0.1-SNAPSHOT.jar"]