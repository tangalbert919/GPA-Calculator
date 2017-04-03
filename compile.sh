#!/bin/sh

echo The GPA Calculator will now be compiled. Please wait...

javac src/io/github/tangalbert919/*.java

echo Compilation succeeded. Building the jar file now...

cd src

jar cvfm ../GPA-Calculator.jar ../manifest.txt io/github/tangalbert919/*.class

echo Build complete. Please run it by using the run shell script \(run.sh\).