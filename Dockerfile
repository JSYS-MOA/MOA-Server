FROM openjdk:21-jdk-slim

COPY . .

RUN chmod +x gradlew && ./gradlew clean build -x test

ENTRYPOINT ["sh", "-c", "java -jar build/libs/*-SNAPSHOT.jar"]