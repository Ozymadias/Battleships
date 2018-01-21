#!/bin/bash

PORT=4321

if [ $# -eq 0 ]
  then
  echo "Running server on default port $PORT"
  else
  PORT=$1
  echo "Running server on port $PORT"
fi

the_output="$(./run_server.sh $PORT&)"

echo "***************"
echo $the_output

sh ./run_client.sh&
sh ./run_client.sh&
