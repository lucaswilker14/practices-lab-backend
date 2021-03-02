FROM openjdk

WORKDIR /evollo/app

COPY target/evollo-backend-0.0.1-SNAPSHOT.jar /evollo/app/evollo-backend.jar

ENTRYPOINT ["java", "-jar", "evollo-backend.jar"]