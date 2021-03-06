package org.jdbchub.sql;

import org.jdbchub.config.DBConfig;
import org.junit.Test;
import static java.util.Arrays.asList;
import static org.jdbchub.JdbcHubTestUtils.dbConfig;
import static org.junit.Assert.assertEquals;

public class DefaultSqlTransformerTest {

	@Test
	public void testTransform() throws Exception {
		DBConfig c = dbConfig("db1", "url1");
		SqlTransformer tr = new DefaultSqlTransformer(c, asList(
			new TestSqlTransfomer(1),
			new TestSqlTransfomer(2)
		));
		assertEquals("select 'v1'", tr.transform("select 'v1'"));
		String sql1 = "select ${db_name}, ${db_url}, 'tr_prop1 tr_prop2 tr_prop3'";
		assertEquals("select 'db1', 'url1', 'v1 v2 tr_prop3'", tr.transform(sql1));
	}

}