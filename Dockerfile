# automate maven build so you dont have to run maven compile and package before running the docker-compose

# uses pre-built docker image as a base for our image, naming it builder to easily access it further
FROM maven:3.8.1-openjdk-11 AS builder

# sets the working directory of the container to /tmp
WORKDIR /tmp

# copies pom.xml to the container
COPY pom.xml .

# ensures all dependencies (mysql specifically) are loaded
RUN mvn dependency:resolve

# copies source files to container
COPY src ./src

# cleans (removes old temporary files), compiles and packages the project
RUN mvn clean package


#uses version 11 of openjdk for compatibility with lab tutorials
FROM openjdk:11

# copies the JAR files to the container
COPY --from=builder /tmp/target/groupProject-v0.1-Ash-jar-with-dependencies.jar app.jar
COPY --from=builder /root/.m2/repository/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar /tmp/lib/mysql-connector-java-8.0.28.jar

# runs the application
ENTRYPOINT ["java", "-cp", "app.jar:/tmp/lib/*", "com.napier.team4groupproject.App"]