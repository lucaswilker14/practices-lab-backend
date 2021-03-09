FROM openjdk

WORKDIR /evollo/app

COPY target/evollo-backend-0.0.1-SNAPSHOT.jar /evollo/app/evollo-backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "evollo-backend.jar", "--host 0.0.0.0"]