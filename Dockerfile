FROM openjdk

WORKDIR /plabs/app

COPY target/practices-lab-backend-0.0.1-SNAPSHOT.jar /plabs/app/plabs-backend.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "plabs-backend.jar", "--host 0.0.0.0"]