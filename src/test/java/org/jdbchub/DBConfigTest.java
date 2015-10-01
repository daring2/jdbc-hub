package org.jdbchub;

import org.junit.Test;

import java.util.Properties;

import static com.typesafe.config.ConfigFactory.parseString;
import static org.junit.Assert.assertEquals;

public class DBConfigTest {

	@Test
	public void testBuild() {
		DBConfig c1 = new DBConfig("c1", parseString("url = url1"));
		assertEquals("c1", c1.name);
		assertEquals("url1", c1.url);
		assertEquals(new Properties(), c1.info);

		DBConfig c2 = new DBConfig("c2", parseString("url = url2, user = u2, password = p2"));
		assertEquals("c2", c2.name);
		assertEquals("url2", c2.url);
		Properties info2 = new Properties();
		info2.setProperty("user", "u2");
		info2.setProperty("password", "p2");
		assertEquals(info2, c2.info);
	}

}