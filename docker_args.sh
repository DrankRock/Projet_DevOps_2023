#!/bin/bash

# DO NOT DELETE THIS FILE

jarFile="/app/Projet_DevOps_2023/ProjectDevOps/target/project-devops-0.0.1.jar"

# Check the argument
if [ "$1" == "test" ]; then
  echo "Running tests .. "
  java -jar ${jarFile}
elif [ "$1" == "run" ]; then
  # Shift is used to shift the arguments to the left, so $1 becomes $0, and 
  # here the $1 run is not necessary anymore, so $1 becomes the first command
  # line argument after the run
  shift
  echo "Running the application with arguments: $@"
  java -jar ${jarFile} "$@" # And there, $@ will show all the arguments after the run
else
  echo "Invalid argument. Please provide 'test' or 'run'."
  exit 1
fi
