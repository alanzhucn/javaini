package org.dtools.ini;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class IniFileWriterTest {

	@Test
	public void test_Writer () throws IOException {
		// create an IniFile object
		IniFile ini = new BasicIniFile();

		// create an IniSection to hold personal data
		IniSection mainSection = new BasicIniSection( "main" );
		ini.addSection( mainSection );

		// create an IniItem for code
		IniItem codeItem = new IniItem( "code");
		codeItem.setValue("ascii");
		mainSection.addItem( codeItem );

		// create an IniItem for age
		IniItem portItem = new IniItem( "port" );
		portItem.setValue("3306");
		mainSection.addItem( portItem );
		
		
		
		File tempIniFile = File.createTempFile("sample", ".ini");
		IniFileWriter writer = new IniFileWriter(ini, tempIniFile, "ASCII");
		writer.write();
		System.out.println("file name: " + tempIniFile.getAbsolutePath());
		Files.copy(Paths.get(tempIniFile.getAbsolutePath()), System.out);
		
		// create an IniItem for age
		IniItem pathItem = new IniItem( "path" );
		pathItem.setValue("c:/项目/test");
		mainSection.addItem( pathItem );
		
		tempIniFile = File.createTempFile("sample-gbk", ".ini");
		writer = new IniFileWriter(ini, tempIniFile, "GBK");
		writer.write();
		System.out.println("file name: " + tempIniFile.getAbsolutePath());
		Files.copy(Paths.get(tempIniFile.getAbsolutePath()), System.out);
		
		
		IniFile gbkIni = new BasicIniFile(false);
		IniFileReader reader = new IniFileReader(gbkIni, tempIniFile, "GBK");
		reader.read();
		mainSection = gbkIni.getSection("main");
		assertEquals(mainSection != null, true);
		assertEquals(mainSection.getItem("path").getValue(), "c:/项目/test");

		

		tempIniFile = File.createTempFile("sample-utf8", ".ini");
		writer = new IniFileWriter(ini, tempIniFile, "UTF-8");
		writer.write();
		System.out.println("file name: " + tempIniFile.getAbsolutePath());
		Files.copy(Paths.get(tempIniFile.getAbsolutePath()), System.out);

		IniFile utf8Ini = new BasicIniFile(false);
		reader = new IniFileReader(utf8Ini, tempIniFile, "UTF-8");
		reader.read();
		mainSection = gbkIni.getSection("main");
		assertEquals(mainSection != null, true);
		assertEquals(mainSection.getItem("path").getValue(), "c:/项目/test");

		
	}
}
