#!/bin/bash
#This script executes server application
#usage: ./run_server.sh <port>

run_server(){
    echo " port $1"
    echo "  _________________________ _____________________ ___ _______    ________    __________________________________   _________________________  ";
    echo " /   _____/\__    ___/  _  \\______   \__    ___/ |   |\      \  /  _____/   /   _____/\_   _____/\______   \   \ /   /\_   _____/\______   \ ";
    echo " \_____  \   |    | /  /_\  \|       _/ |    |   |   |/   |   \/   \  ___   \_____  \  |    __)_  |       _/\   Y   /  |    __)_  |       _/ ";
    echo " /        \  |    |/    |    \    |   \ |    |   |   /    |    \    \_\  \  /        \ |        \ |    |   \ \     /   |        \ |    |   \ ";
    echo "/_______  /  |____|\____|__  /____|_  / |____|   |___\____|__  /\______  / /_______  //_______  / |____|_  /  \___/   /_______  / |____|_  / ";
    echo "        \/                 \/       \/                       \/        \/          \/         \/         \/                   \/         \/  ";
    java -Dport=$1 -jar ../server/target/server*.jar
}

if [ $# -eq 0 ]; then
  echo "Usage: ${0} <port>"
  exit 1;
elif [[ $1 =~ ^[0-9]{4,5}$ ]]; then
    run_server $1;
else
    echo "$1 is incorrect port number. Please, use number between 1024 and 65535"
fi

