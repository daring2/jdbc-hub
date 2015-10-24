package org.jdbchub.sql;

import org.jdbchub.config.DBConfig;
import org.junit.Test;
import static com.typesafe.config.ConfigFactory.parseString;
import static org.junit.Assert.assertEquals;

public class DefaultSqlTransformerTest {

	@Test
	public void testTransform() throws Exception {
		DBConfig c = new DBConfig("db1", parseString("url = url1"));
		SqlTransformer tr = new DefaultSqlTransformer(c);
		assertEquals("select 'v1'", tr.transform("select 'v1'"));
		assertEquals("select 'db1'", tr.transform("select '${db_name}'"));
	}

}