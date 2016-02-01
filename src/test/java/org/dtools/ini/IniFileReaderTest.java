/**
 * 
 */
package org.dtools.ini;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * @author alanzhu.cn
 * @version 1.1.2
 * @since 1.1.2
 *
 */
public class IniFileReaderTest {

	public static void printIni (IniFile ini){

		for(int i=0;i<ini.getNumberOfSections();i++){
			IniSection sec = ini.getSection(i);
			System.out.println("---- [" + sec.getName() + "] ----");
			for(IniItem item : sec.getItems()){
				System.out.println("[" + item.getName() + "] = [" + item.getValue() + "]");
			}
		}

	}

	@Test
	public void test_iniFileASCIIEncoding () throws IOException {

		String iniFileName = "/sample-ascii.ini";
		String fileName = IniFileReaderTest.class.getResource(iniFileName).getFile();

		IniFile ini = new BasicIniFile(false);
		IniFileReader reader = new IniFileReader(ini, new File(fileName));
		reader.read();
		printIni(ini);

		IniSection mainSection = ini.getSection("main");
		assertEquals(mainSection != null, true);
		assertEquals(mainSection.getItem("code").getValue(), "ascii");
		assertEquals(mainSection.getItem("port").getValue(), "3306");

		IniSection mysqlSection = ini.getSection("mysql");
		assertEquals(mysqlSection != null, true);
		assertEquals(mysqlSection.getItem("default-character-set").getValue(), "ascii");
		assertEquals(mysqlSection.getItem("basedir").getValue(), "\"C:/mysql/MySQL Server 5.6/\"");

		IniSection globalSection = ini.getSection("GLOBAL");
		assertEquals(globalSection != null, true);
		assertEquals(globalSection.getItem("path").getValue(), "c:\\test");

		IniSection jdbcSection = ini.getSection("JDBC");
		assertEquals(jdbcSection != null, true);
		assertEquals(jdbcSection.getItem("driver").getValue(), "com.mysql.jdbc.Driver");
		assertEquals(jdbcSection.getItem("url").getValue(), "jdbc:mysql://localhost:3306/alan");
	}

	@Test
	public void test_iniFileGBKEncoding () throws IOException {

		String encoding = "GBK";
		String iniFileName = "/sample-gbk.ini";
		String fileName = IniFileReaderTest.class.getResource(iniFileName).getFile();

		IniFile ini = new BasicIniFile(false);
		IniFileReader reader = new IniFileReader(ini, new File(fileName), encoding);
		reader.read();

		printIni(ini);


		IniSection mainSection = ini.getSection("main");
		assertEquals(mainSection != null, true);
		assertEquals(mainSection.getItem("code").getValue(), "GBK 国  标");
		assertEquals(mainSection.getItem("port").getValue(), "3306");

		IniSection mysqlSection = ini.getSection("mysql");
		assertEquals(mysqlSection != null, true);
		assertEquals(mysqlSection.getItem("default-character-set").getValue(), "国标");
		assertEquals(mysqlSection.getItem("basedir").getValue(), "\"C:/我的数据库/mysql/MySQL Server 5.6/\"");

		IniSection globalSection = ini.getSection("GLOBAL");
		assertEquals(globalSection != null, true);
		assertEquals(globalSection.getItem("path").getValue(), "c:\\项目A\\test");

		IniSection jdbcSection = ini.getSection("JDBC setting");
		assertEquals(jdbcSection != null, true);
		assertEquals(jdbcSection.getItem("driver").getValue(), "com.mysql.jdbc.Driver");
		assertEquals(jdbcSection.getItem("url").getValue(), "jdbc:mysql://localhost:3306/alan");
	}

	@Test
	public void test_iniFileUTF8Encoding () throws IOException {

		String encoding = "UTF-8";
		String iniFileName = "/sample-utf8.ini";
		String fileName = IniFileReaderTest.class.getResource(iniFileName).getFile();

		IniFile ini = new BasicIniFile(false);
		IniFileReader reader = new IniFileReader(ini, new File(fileName), encoding);
		reader.read();

		printIni(ini);


		IniSection mainSection = ini.getSection("main");
		assertEquals(mainSection != null, true);
		assertEquals(mainSection.getItem("code").getValue(), "GBK 国  标");
		assertEquals(mainSection.getItem("port").getValue(), "3306");

		IniSection mysqlSection = ini.getSection("mysql");
		assertEquals(mysqlSection != null, true);
		assertEquals(mysqlSection.getItem("default-character-set").getValue(), "国标");
		assertEquals(mysqlSection.getItem("basedir").getValue(), "\"C:/我的数据库/mysql/MySQL Server 5.6/\"");

		IniSection globalSection = ini.getSection("GLOBAL");
		assertEquals(globalSection != null, true);
		assertEquals(globalSection.getItem("path").getValue(), "c:\\项目A\\test");

		IniSection jdbcSection = ini.getSection("JDBC setting");
		assertEquals(jdbcSection != null, true);
		assertEquals(jdbcSection.getItem("driver").getValue(), "com.mysql.jdbc.Driver");
		assertEquals(jdbcSection.getItem("url").getValue(), "jdbc:mysql://localhost:3306/alan");
	}
}
