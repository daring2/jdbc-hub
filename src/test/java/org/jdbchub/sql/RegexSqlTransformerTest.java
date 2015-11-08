package org.jdbchub.sql;

import com.typesafe.config.Config;
import org.jdbchub.config.DBConfig;
import org.junit.Test;
import static org.jdbchub.config.ConfigUtils.configFromString;
import static org.junit.Assert.*;

public class RegexSqlTransformerTest {

	@Test
	public void testIsEnabled() {
		DBConfig dbc1 = new DBConfig("dbc1", configFromString("url = url1"));
		DBConfig dbc2 = new DBConfig("dbc1", configFromString("url = url2"));

		Config c1 = configFromString("rules {}");
		RegexSqlTransformer tr1 = new RegexSqlTransformer(c1);
		assertTrue(tr1.isEnabled(dbc1));
		assertTrue(tr1.isEnabled(dbc2));

		Config c2 = configFromString("urlPattern = \".*1\", rules {}");
		RegexSqlTransformer tr2 = new RegexSqlTransformer(c2);
		assertTrue(tr2.isEnabled(dbc1));
		assertFalse(tr2.isEnabled(dbc2));
	}

	@Test
	public void testTransform() {
		Config c = configFromString("rules { \"k1\" = \"v1\", \"k2(.)\" = \"v2$1\" } ");
		RegexSqlTransformer tr = new RegexSqlTransformer(c);
		assertEquals(2, tr.rules.size());
		assertEquals("k0", tr.transform("k0"));
		assertEquals("k0 v1 v21 v22 k31", tr.transform("k0 k1 k21 k22 k31"));
	}

}