FROM java:openjdk-8-alpine

ARG JAR_FILE
ADD build/libs/sfinapp-1.0.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
