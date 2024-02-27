FROM gradle:7.6-jdk11

WORKDIR /app

COPY . .

RUN gradle clean build

EXPOSE 8080

CMD ["java", "-jar", "build/libs/crud-0.0.1-SNAPSHOT.jar"]
