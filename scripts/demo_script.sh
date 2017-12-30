#!/bin/bash
set -e

LOG_FILE="$PWD/"clone.log

if [ $# -eq 0 ]
  then
  echo "Usage: ./demo_script.sh <dir where repository will be cloned>"
  exit 1;
fi

echo "Cloning git repository"
git clone https://github.com/szczepanskikrs/Battleships.git $1 >$LOG_FILE
read -p "Clone successful! Do want to run tests now(Y/N)?"
if [ "${REPLY^^}" != "Y" ]; then
   exit;
fi
cd $1/scripts
sh ./deployment_script.sh
