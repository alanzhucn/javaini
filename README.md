
`javaini` is branch of javaini version 1.1.1, the dTool's Java INI Package (http://sourceforge.net/projects/javaini/) with bugfixes and more. 
Original License: LGPLv2

The project is a maven project.
Encoding support is introduced. 

## build instruction
```
mvn clean comile package
mvn test
```

## example

Please see the see the [`tests`](src/test/java/org/dtools/ini/) folder.

## pros

This is a small package for ini file operation.

## cons

- Simple interfact to add name-value pair would be expected. Such as following:
```
section.addNameValue("name", "value"); 
section.addNameValue("name", "value", "line end comment", "preComment", "postComment");
```
- Only file read/write interfaces are provided. ini2String is a private method. 

The ini file read and write are done by IniFileReader and IniFileWriter.
And these two are bound to File object. It would be better to have the input from inputsteam and have the output to outputsteam.


## alternative

- ini4j http://ini4j.sourceforge.net/
- shiro ini utility in project shiro
- TinyIni part of the tinyframework. See http://www.oschina.net/p/tinyini and http://git.oschina.net/tinyframework/tiny/tree/master/framework/org.tinygroup.ini
