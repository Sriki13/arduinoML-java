#!/usr/bin/env bash

cd example
mvn -q exec:java -Dexec.args="$1"