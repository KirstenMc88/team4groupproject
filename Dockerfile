# automate maven build so you dont have to run maven compile and package before running the docker-compose
FROM maven:3.8.1-openjdk-11 AS builder

WORKDIR /tmp

COPY pom.xml .
RUN mvn dependency:resolve
COPY src ./src
RUN mvn clean package


#uses version 11 of openjdk for compatibility with lab tutorials
FROM openjdk:11

# sets the working directory to /tmp
WORKDIR /tmp
# copies the JAR files to the container
COPY --from=builder /tmp/target/groupProject-1.0-SNAPSHOT.jar app.jar
COPY --from=builder /root/.m2/repository/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar /tmp/lib/mysql-connector-java-8.0.28.jar

# runs the application
ENTRYPOINT ["java", "-cp", "app.jar:/tmp/lib/*", "com.napier.team4groupproject.App"]