#!/bin/bash
#usage: ./run_client <optional: language_parameter>
#possible languages parameters: PL, EN

#Define default language here
LANGUAGE=${1:-"EN"}

java -jar ../client/target/client*.jar $LANGUAGE
