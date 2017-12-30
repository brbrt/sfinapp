FROM java:openjdk-8-alpine

#ARG JAR_FILE
ADD sfinapp.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
