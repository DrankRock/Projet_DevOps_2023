FROM ubuntu:22.04

# Java install base from : https://stackoverflow.com/a/44058196

# Install OpenJDK-18
RUN apt-get update && \
    apt-get install -y openjdk-18-jdk && \
    apt-get install -y ant && \
    apt-get install -y maven && \
    apt-get clean;

# Setup JAVA_HOME -- useful for docker commandline
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
RUN export JAVA_HOME

# check if java works
CMD ["java", "-version"]