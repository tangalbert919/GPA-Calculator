#!/bin/sh

javac src/io/github/tangalbert919/*

jar cvf GPA-Calculator.jar src/io/github/tangalbert919/IOFile.class src/io/github/tangalbert919/Main.class
