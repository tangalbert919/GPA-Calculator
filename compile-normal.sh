#!/bin/sh

echo The command-line version of the GPA Calculator will now be compiled.

echo Please wait while we compile the files...

javac src/io/github/tangalbert919/Main.java src/io/github/tangalbert919/IOFile.java

echo Compilation succeeded. Building the jar file now...

cd src

jar cvfm ../GPA-Calculator.jar ../Manifest/manifest.txt io/github/tangalbert919/*.class

echo Build complete. Please run it by using the run shell script \(run.sh\).

rm io/github/tangalbert919/*.class
