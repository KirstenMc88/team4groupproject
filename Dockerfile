#uses the latest version of opejdk
FROM openjdk:latest

# copies the class files from the local machine to the com directory
COPY ./target/classes/com /tmp/com

# sets the working directory to /tmp
WORKDIR /tmp

# runs the application
ENTRYPOINT ["java", "com.napier.team4groupproject.App"]