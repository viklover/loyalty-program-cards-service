FROM gradle:8.4.0-jdk21-alpine
WORKDIR /
ADD . .
RUN gradle build --stacktrace && /bin/mv /build/libs/cards-0.0.1-SNAPSHOT.jar /cards-service.jar

FROM openjdk:22-jdk-slim
WORKDIR /
COPY --from=0 /cards-service.jar /cards-service.jar
EXPOSE 8000
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "cards-service.jar"]
