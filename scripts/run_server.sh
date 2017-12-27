#!/bin/bash
set -e
if [ $# -eq 0 ]
  then
  echo "Usage: ./run_server.sh <port>"
  exit 1;
fi

java -Dport=$1 -jar ../server/target/server-1.0.jar
