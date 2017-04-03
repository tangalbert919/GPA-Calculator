echo Compiling java files...

javac src/io/github/tangalbert919/*.java

echo Compilation complete. Building the jar file now...

cd src

jar cvfm ../GPA-Calculator.jar ../manifest.txt src/io/github/tangalbert919/*.class

echo Build complete. Please run it by using the batch file \(run.bat\).

PAUSE
