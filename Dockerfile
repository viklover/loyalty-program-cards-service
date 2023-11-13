FROM openjdk:21
EXPOSE 80
WORKDIR /app
COPY . .
CMD ["./gradlew", "build"]

ADD /build/libs/cards-0.0.1-SNAPSHOT.jar cards-service.jar
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "cards-service.jar"]
