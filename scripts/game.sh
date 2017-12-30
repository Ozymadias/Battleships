#!/bin/bash
echo "Running server on default port 4321 in order to change port please use run_server.sh<port>"
sh ./run_server.sh 4321&
sh ./run_client.sh&
sh ./run_client.sh&
