echo Select the type of calculator you want to build.
echo Press 1 for a GUI build.
echo Press 2 for a command line build.

SET INPUT=
SET /P INPUT=Select an option: 

IF /I '%INPUT%'=='1' GOTO gui
IF /I '%INPUT%'=='2' GOTO command-line

:gui
echo GUI build selected. Compiling java files...

javac src/io/github/tangalbert919/Main2.java src/io/github/tangalbert919/IOFile.java src/io/github/tangalbert919/GPACalcGUI.java src/io/github/tangalbert919/HelpFile.java src/io/github/tangalbert919/TemplateFile.java

echo Compilation complete. Building the jar file now...

cd src

jar cvfm ../GPA-Calculator.jar ../Manifest/manifest2.txt io/github/tangalbert919/*.class

echo Build complete. Please run it by using the batch file \(run.bat\).

cd io\github\tangalbert919

del /f IOFile.class,Main2.class

PAUSE

:command-line
echo Command line build selected. Compiling java files...

javac src/io/github/tangalbert919/Main.java src/io/github/tangalbert919/IOFile.java src/io/github/tangalbert919/HelpFile.java src/io/github/tangalbert919/TemplateFile.java

echo Compilation complete. Building the jar file now...

cd src

jar cvfm ../GPA-Calculator.jar ../Manifest/manifest.txt io/github/tangalbert919/*.class

echo Build complete. Please run it by using the batch file \(run.bat\).

cd io\github\tangalbert919

del /f IOFile.class,Main.class

PAUSE

