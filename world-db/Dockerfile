# Use the MySQL image version 8.3
FROM mysql:8.3

# Set the working directory
WORKDIR /tmp

# Copy world.sql to the working directory
COPY world.sql /tmp/

# Copy world.sql to docker-entrypoint-initdb.d to execute on container startup
COPY world.sql /docker-entrypoint-initdb.d

# Set the root password TODO change to a proper one?
ENV MYSQL_ROOT_PASSWORD example