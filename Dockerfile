# Use the official OpenJDK 18 image as the base image
FROM openjdk:18-slim AS build

WORKDIR /app

# Install required packages and tools
RUN apt-get update && \
    apt-get install -y wget curl gnupg

# Add the official Maven repository
RUN echo "deb https://dl.your-server.de/pub/maven2/ ./" > /etc/apt/sources.list.d/maven.list && \
    wget -q -O - https://www.apache.org/dist/maven/KEYS | gpg --import && \
    wget -q -O - https://www.apache.org/dist/maven/KEYS | gpg --export | apt-key add -

# Install Maven
RUN apt-get update && \
    apt-get install -y maven

# Verify the installed Maven and JDK versions
RUN mvn --version
RUN java --version

# Copy files from file system
COPY ProjectDevOps/* .
RUN mvn clean package