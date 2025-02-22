FROM adoptopenjdk:11-jre-hotspot
COPY target/movies-catalog-api-0.0.5-SNAPSHOT.jar java-movies-challenge-api.jar
ENTRYPOINT ["java", "-jar", "java-movies-challenge-api.jar"]