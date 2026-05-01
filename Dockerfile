FROM amazoncorretto:21-al2023-jdk

COPY . .

RUN chmod +x gradlew && ./gradlew clean build -x test

ENTRYPOINT ["sh", "-c", "java -jar build/libs/*.jar"]