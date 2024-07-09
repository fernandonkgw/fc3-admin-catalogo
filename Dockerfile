FROM eclipse-temurin:17.0.10_7-jre-alpine

COPY build/libs/*.jar /opt/app/application.jar

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

CMD java -jar /opt/app/application.jar

# buildar a aplicacao
#./gradlew bootJar
