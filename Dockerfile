FROM bellsoft/liberica-openjdk-alpine:21

COPY . .

RUN chmod +x gradlew && ./gradlew clean build -x test

ENTRYPOINT ["sh", "-c", "java -jar build/libs/*[!plain].jar"]