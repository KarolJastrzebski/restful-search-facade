FROM openjdk:8-jdk-alpine
COPY build/libs/*.jar app.jar
EXPOSE 6868
EXPOSE 5005
ENV SERVER_PORT 6868
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005", "-jar", "app.jar"]
