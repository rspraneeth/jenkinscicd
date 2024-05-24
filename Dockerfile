FROM openjdk:17
WORKDIR /appContainer
COPY ./target/jenkinscicd.jar /appContainer
EXPOSE 8282
CMD ["java", "-jar", "jenkinscicd.jar"]
