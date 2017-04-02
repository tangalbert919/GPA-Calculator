#!/bin/sh

javac src/io/github/tangalbert919/*.java

cd src

jar cvfm ../GPA-Calculator.jar ../manifest.txt io/github/tangalbert919/*.class
