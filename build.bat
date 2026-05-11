@echo off
echo Compiling Java Tetris...
if not exist "bin" mkdir bin
javac -d bin src\com\tetris\main\*.java src\com\tetris\game\*.java src\com\tetris\gui\*.java src\com\tetris\input\*.java
if %errorlevel% neq 0 (
    echo Compilation failed!
    pause
) else (
    echo Compilation successful!
)
