FROM openjdk:latest
COPY ./groupProject/target/classes/com /tmp/com
WORKDIR /tmp
ENTRYPOINT ["java", "com.napier.team4groupproject.App"]