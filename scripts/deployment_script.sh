#!/bin/bash
set -e
LOG_FILE="$PWD/"test.log
TMP_TI_LOG_FILE="$PWD/"test_ti_tmp.log

echo "___________ ___________   _________ ___________ .___   _______      ________  ";
echo "\__    ___/ \_   _____/  /   _____/ \__    ___/ |   |  \      \    /  _____/  ";
echo "  |    |     |    __)_   \_____  \    |    |    |   |  /   |   \  /   \  ___  ";
echo "  |    |     |        \  /        \   |    |    |   | /    |    \ \    \_\  \ ";
echo "  |____|    /_______  / /_______  /   |____|    |___| \____|__  /  \______  / ";
echo "                    \/          \/                            \/          \/  ";

cd ..

START=$(date +%s)
mvn clean test > "$LOG_FILE"
END=$(date +%s)
echo "************************"
echo "Summary of unit tests results"
echo "************************"
DIFF=$((END - START))
echo "Running unit tests took $DIFF seconds"
grep -A2 Results "$LOG_FILE" |grep "Tests run" | awk 'BEGIN { FS = "[:,]" } { sumRun+=$2; sumFailures+=$4; sumErrors+=$6; sumSkipped+=$8 } END {print "Total tests run:" sumRun;print "Failures:" sumFailures;print "Errors:" sumErrors;print "Skipped:" sumSkipped} '

mvn -pl :common install > "$LOG_FILE"
touch "$TMP_TI_LOG_FILE"
START=$(date +%s)
mvn failsafe:integration-test > "$TMP_TI_LOG_FILE"
cat "$TMP_TI_LOG_FILE" >> "$LOG_FILE"
END=$(date +%s)
echo "************************"
echo "Summary of integration tests results"
echo "************************"
DIFF=$((END - START))
echo "Running integration tests took $DIFF seconds"
grep -A2 Results "$TMP_TI_LOG_FILE" |grep "Tests run" | awk 'BEGIN { FS = "[:,]" } { sumRun+=$2; sumFailures+=$4; sumErrors+=$6; sumSkipped+=$8 } END {print "Total tests run:" sumRun;print "Failures:" sumFailures;print "Errors:" sumErrors;print "Skipped:" sumSkipped} '
rm "$TMP_TI_LOG_FILE"

read -p "You are about to run MVN INSTALL do you want to continue(Y/N)?"
if [ "${REPLY^^}" != "Y" ]; then
      exit;
fi

echo ".___   _______      _________ ___________    _____    .____      .____      .___   _______      ________    ";
echo "|   |  \      \    /   _____/ \__    ___/   /  _  \   |    |     |    |     |   |  \      \    /  _____/    ";
echo "|   |  /   |   \   \_____  \    |    |     /  /_\  \  |    |     |    |     |   |  /   |   \  /   \  ___    ";
echo "|   | /    |    \  /        \   |    |    /    |    \ |    |___  |    |___  |   | /    |    \ \    \_\  \   ";
echo "|___| \____|__  / /_______  /   |____|    \____|__  / |_______ \ |_______ \ |___| \____|__  /  \______  /   ";
echo "              \/          \/                      \/          \/         \/               \/          \/    ";

mvn install >> "$LOG_FILE"
echo "**********************"
echo "MVN INSTALL successful"
echo "**********************"
read -p "You are about to create documentation do you want to continue(Y/N)?"
if [ "${REPLY^^}" != "Y" ]; then
   exit;
fi

echo "_________   __________  ___________    _____    ___________ .___   _______      ________  ";
echo "\_   ___ \  \______   \ \_   _____/   /  _  \   \__    ___/ |   |  \      \    /  _____/  ";
echo "/    \  \/   |       _/  |    __)_   /  /_\  \    |    |    |   |  /   |   \  /   \  ___  ";
echo "\     \____  |    |   \  |        \ /    |    \   |    |    |   | /    |    \ \    \_\  \ ";
echo " \______  /  |____|_  / /_______  / \____|__  /   |____|    |___| \____|__  /  \______  / ";
echo "        \/          \/          \/          \/                            \/          \/  ";

(mvn site:site && mvn site:stage && mvn site:deploy) >> "$LOG_FILE"
echo "************************"
echo "Documentation generated!"
echo "************************"
NO_OF_COMMITS=$(git rev-list master --count)
NO_OF_INTERFACES=$(grep --include=\*.java -r "interface.*{" | wc -l)
NO_OF_PACKAGES=$(grep --include=\*.java -r package | awk 'BEGIN { FS = ":" } {print $2} ' |sort |uniq |wc -l)
NO_OF_PUBLIC_METHODS=$(grep -r "public" --include='*.java' . | grep -v "class\|interface\|enum\|test" | grep "(*)" | wc -l)
NO_OF_PROTECTED_METHODS=$(grep -r "protected" --include='*.java' . | grep -v "class\|interface\|enum\|test" | grep "(*)" | wc -l)
NO_OF_PACKAGE_PRIVATE_METHODS=$(egrep -xr '[[:space:]]+(final|static)?[[:space:]]+[a-zA-Z0-9]+[[:space:]][a-zA-Z0-9]+\(.*' --include='*.java' . | egrep -v "public|private|protected|integrationtests|test|new|return" | wc -l)
NO_OF_PRIVATE_METHODS=$(grep -r "private" --include='*.java' . | grep -v "class\|interface\|enum\|test|\final" | grep "(*)" | wc -l)
NO_OF_FINAL_VARIABLES=$(grep -r "final" --include='*.java' .| wc -l)

echo "Total number of commits"
echo "$NO_OF_COMMITS"
echo "Number of commits by user"
git shortlog -sn
echo "Number of interfaces"
echo "$NO_OF_INTERFACES"
echo "Number of packages"
echo "$NO_OF_PACKAGES"
echo "Number of public methods"
echo "$NO_OF_PUBLIC_METHODS"
echo "Number of protected methods"
echo "$NO_OF_PROTECTED_METHODS"
echo "Number of package-private methods"
echo "$NO_OF_PACKAGE_PRIVATE_METHODS"
echo "Number of private methods"
echo "$NO_OF_PRIVATE_METHODS"
echo "Number of final variables"
echo "$NO_OF_FINAL_VARIABLES"
echo "Number of lines of Java code"
wc -l `find -name '*.java'` |grep total | awk '{ print $1}'
read -p "You are about to create Sonar Qube report! In order for this step to succeed you need to have Sonar server running on your system! Do you want to continue(Y/N)?"
if [ "${REPLY^^}" != "Y" ]; then
echo "*************************"
echo "Skipping Sonar execution!"
echo "*************************"
echo "  _________  ____ ___  _________   _________   ___________   _________   _________ ";
echo " /   _____/ |    |   \ \_   ___ \  \_   ___ \  \_   _____/  /   _____/  /   _____/ ";
echo " \_____  \  |    |   / /    \  \/  /    \  \/   |    __)_   \_____  \   \_____  \  ";
echo " /        \ |    |  /  \     \____ \     \____  |        \  /        \  /        \ ";
echo "/_______  / |______/    \______  /  \______  / /_______  / /_______  / /_______  / ";
echo "        \/                     \/          \/          \/          \/          \/  ";
exit;
fi
echo "  _________ ________     _______       _____    __________  ";
echo " /   _____/ \_____  \    \      \     /  _  \   \______   \ ";
echo " \_____  \   /   |   \   /   |   \   /  /_\  \   |       _/ ";
echo " /        \ /    |    \ /    |    \ /    |    \  |    |   \ ";
echo "/_______  / \_______  / \____|__  / \____|__  /  |____|_  / ";
echo "        \/          \/          \/          \/          \/  ";
mvn org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar >> "$LOG_FILE"
echo "***************************"
echo "Successful Sonar execution!"
echo "***************************"
echo "  _________  ____ ___  _________   _________   ___________   _________   _________ ";
echo " /   _____/ |    |   \ \_   ___ \  \_   ___ \  \_   _____/  /   _____/  /   _____/ ";
echo " \_____  \  |    |   / /    \  \/  /    \  \/   |    __)_   \_____  \   \_____  \  ";
echo " /        \ |    |  /  \     \____ \     \____  |        \  /        \  /        \ ";
echo "/_______  / |______/    \______  /  \______  / /_______  / /_______  / /_______  / ";
echo "        \/                     \/          \/          \/          \/          \/  ";

