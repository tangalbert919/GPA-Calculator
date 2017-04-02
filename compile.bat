echo Compiling java files...

javac src/io/github/tangalbert919/*.java

cd src

jar cvfm ../GPA-Calculator.jar ../manifest.txt src/io/github/tangalbert919/*.class

echo DONE.

PAUSE
