#!/bin/bash


# Compile the Java program that uses a script written in Autumn. 
javac -cp "build/lib/autumn/*" "src/example/Main.java" -d "build"

# Create a Jar file.
cd "build"
jar -cvf "program.jar" "example"

# Execute the compiled Java program.
java -cp "lib/autumn/*:program.jar" example.Main

