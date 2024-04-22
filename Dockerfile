FROM openjdk:21-jdk
WORKDIR /app
COPY target/Lab1-B-0.0.1-SNAPSHOT.jar /app/Lab1-B-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "Lab1-B-0.0.1-SNAPSHOT.jar"]
LABEL authors="Vedran Deskovski"