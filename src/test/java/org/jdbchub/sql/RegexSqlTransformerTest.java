package org.jdbchub.sql;

import com.typesafe.config.Config;
import org.jdbchub.config.DBConfig;
import org.junit.Test;
import static org.jdbchub.JdbcHubTestUtils.dbConfig;
import static org.jdbchub.config.ConfigUtils.configFromString;
import static org.junit.Assert.*;

public class RegexSqlTransformerTest {

	@Test
	public void testIsEnabled() {
		DBConfig dbc1 = dbConfig("dbc1", "url1");
		DBConfig dbc2 = dbConfig("dbc2", "url2");
		DBConfig dbc3 = dbConfig("dbc3", "url3");

		Config c1 = configFromString("rules {}");
		RegexSqlTransformer tr1 = new RegexSqlTransformer(c1);
		assertTrue(tr1.isEnabled(dbc1));
		assertTrue(tr1.isEnabled(dbc2));

		Config c2 = configFromString("urlFilter = \".*(1|2)\", urlNotFilter = \".*2\", rules {}");
		RegexSqlTransformer tr2 = new RegexSqlTransformer(c2);
		assertTrue(tr2.isEnabled(dbc1));
		assertFalse(tr2.isEnabled(dbc2));
		assertFalse(tr2.isEnabled(dbc3));
	}

	@Test
	public void testTransform() {
		Config c = configFromString("rules { \"k1\" = \"v1\", \"k2(.)\" = \"v2$1\" } ");
		RegexSqlTransformer tr = new RegexSqlTransformer(c);
		assertEquals(2, tr.rules.size());
		assertEquals("k0", tr.transform("k0"));
		assertEquals("k0 v1 v21 v22 k31", tr.transform("k0 k1 k21 K22 k31"));
	}

}