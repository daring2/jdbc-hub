package org.jdbchub.sql;

import com.typesafe.config.Config;
import org.junit.Test;
import static org.jdbchub.config.ConfigUtils.configFromString;
import static org.junit.Assert.assertEquals;

public class RegexSqlTransformerTest {

	@Test
	public void testTransform() {
		Config c = configFromString("rules { \"k1\" = \"v1\", \"k2(.)\" = \"v2$1\" } ");
		RegexSqlTransformer tr = new RegexSqlTransformer(c);
		assertEquals(2, tr.rules.size());
		assertEquals("k0", tr.transform("k0"));
		assertEquals("k0 v1 v21 v22 k31", tr.transform("k0 k1 k21 k22 k31"));
	}

}