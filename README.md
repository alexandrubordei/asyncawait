# asyncawait example in java

To import in intel jidea use 'import from Gradle'.

To build (with tests):
```
gradle build
```
To create a zip with all dependencies:

```
gradle distZip
```

To test the code without unzipping the zip on Linux/Mac:
```
gradle installDist
cd ./build/install/asyncawait
./bin/asyncawait
```
To test the code without unzipping the zip on Windows:
```
gradle installDist
cd \build\install\asyncawait
bin\asyncawait.bat
```
