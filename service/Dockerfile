FROM java:openjdk-8-alpine

RUN apk update && apk add bash libstdc++ && rm -rf /var/cache/apk/*

ADD . /webapp
WORKDIR /webapp

RUN ./gradlew build

EXPOSE 4060
CMD ["java", "-Dserver.port=4060", "-jar", "build/libs/sfinapp-0.0.1-SNAPSHOT.jar"]

