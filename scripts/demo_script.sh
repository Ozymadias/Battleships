#!/bin/bash
set -e

LOG_FILE="$PWD/"demo.log

if [ $# -eq 0 ]
  then
  echo "Usage: ./demo_script.sh <dir where repository will be cloned>"
  exit 1;
fi

echo "Cloning git repository"
git clone https://github.com/szczepanskikrs/Battleships.git $1 >$LOG_FILE
echo "**********************"
echo "Running mvn clean test"
echo "**********************"
cd $1
mvn clean test >> $LOG_FILE
echo "************************"
echo "Summary of tests results"
echo "************************"
cat $LOG_FILE |grep -A2 Results |grep "Tests run" | awk 'BEGIN { FS = "[:,]" } { sumRun+=$2; sumFailures+=$4; sumErrors+=$6; sumSkipped+=$8 } END {print "Total tests run:" sumRun;print "Failures:" sumFailures;print "Errors:" sumErrors;print "Skipped:" sumSkipped} '
echo "*******************"
echo "Running mvn install"
echo "*******************"
mvn install >>$LOG_FILE
echo "*********************************"
echo "Running mvn checkstyle:checkstyle"
echo "*********************************"
mvn checkstyle:checkstyle >> $LOG_FILE
echo "**********************************"
echo "Running mvn site && mvn site:stage"
echo "**********************************"
(mvn site && mvn site:stage) >>$LOG_FILE
echo "***********************"
echo "Running mvn sonar:sonar"
echo "***********************"
mvn org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar >>$LOG_FILE
firefox target/client/target/client/checkstyle.html
firefox target/server/target/server/checkstyle.html
firefox target/common/target/common/checkstyle.html
firefox target/site/index.html
firefox https://github.com/szczepanskikrs/Battleships/blob/master/Estimates.md
firefox https://github.com/szczepanskikrs/Battleships/blob/master/Requirements.md
echo "****************"
echo "***SUCCESS!!!***"
echo "****************"
