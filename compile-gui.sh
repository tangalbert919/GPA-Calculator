#!/bin/sh

echo The GUI version of the GPA Calculator will now be compiled.

echo Please wait while we compile the files...

javac src/io/github/tangalbert919/Main2.java src/io/github/tangalbert919/IOFile.java src/io/github/tangalbert919/GPACalcGUI.java src/io/github/tangalbert919/HelpFile.java src/io/github/tangalbert919/TemplateFile.java

echo Compilation succeeded. Building the jar file now...

cd src

jar cvfm ../GPA-Calculator.jar ../Manifest/manifest2.txt ./io/github/tangalbert919/*.class

echo Build complete. Please run it by using the run shell script \(run.sh\).

rm io/github/tangalbert919/*.class
