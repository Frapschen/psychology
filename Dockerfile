FROM adoptopenjdk/openjdk11:latest
VOLUME /tmp
ADD target/psychology-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
