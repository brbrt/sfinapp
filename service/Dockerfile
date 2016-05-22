FROM java:openjdk-8-alpine

ENV MAVEN_VERSION="3.3.9" \
    M2_HOME=/usr/lib/mvn

RUN apk add --update wget && \
  cd /tmp && \
  wget "http://xenia.sote.hu/ftp/mirrors/www.apache.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz" && \
  tar -zxvf "apache-maven-$MAVEN_VERSION-bin.tar.gz" && \
  mv "apache-maven-$MAVEN_VERSION" "$M2_HOME" && \
  ln -s "$M2_HOME/bin/mvn" /usr/bin/mvn && \
  apk del wget && \
  rm /tmp/* /var/cache/apk/*


WORKDIR /webapp

ADD pom.xml /webapp
RUN mvn verify clean --fail-never

ADD . /webapp

RUN mvn package -DskipTests

EXPOSE 4060
CMD ["java", "-Dhttp.port=4060", "-Ddb.url=jdbc:h2:mem:sfinapptest", "-cp", ".:target/sfinapp-service-standalone.jar", "hu.rbr.sfinapp.App"]
