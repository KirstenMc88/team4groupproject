#uses the latest version of opejdk
FROM openjdk:latest
# copies the class files from the local machine to the com directory
COPY ./target/groupProject-v0.1-Ash-jar-with-dependencies.jar /tmp
# sets the working directory to /tmp
WORKDIR /tmp
# runs the application
ENTRYPOINT ["java", "-jar", "groupProject-v0.1-Ash-jar-with-dependencies.jar"]