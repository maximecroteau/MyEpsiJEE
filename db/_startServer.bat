set HSQLDB_LIB=hsqldb.jar

"C:\Program Files\Java\jdk1.8.0_151\bin\java.exe" -classpath %HSQLDB_LIB% org.hsqldb.Server -database.0 file:projet1 -port 9003

pause