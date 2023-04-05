#!/bin/bash

# DO NOT DELETE THIS FILE

jarFile="/app/target/project-devops-0.0.1.jar"

# Check the argument
if [ "$1" == "test" ]; then
  echo "Running tests .. "
  java -jar ${jarFile}
elif [ "$1" == "run" ]; then
  # Remove the first argument ("run") and pass the remaining arguments to the JAR file
  shift
  echo "Running the application with arguments: $@"
  java -jar ${jarFile} "$@"
else
  echo "Invalid argument. Please provide 'test' or 'run'."
  exit 1
fi