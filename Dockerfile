FROM bellsoft/liberica-openjdk-alpine:21

COPY . .

RUN chmod +x gradlew && ./gradlew clean build -x test

ENTRYPOINT ["sh", "-c", "java -Djava.net.preferIPv4Stack=true -Dserver.port=8080 -jar build/libs/*[!plain].jar"]