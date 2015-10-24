package org.jdbchub.jdbc;

import org.jdbchub.config.DBConfig;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import static java.util.Arrays.asList;
import static org.jdbchub.JdbcHubTestUtils.selectStringList;
import static org.jdbchub.config.ConfigUtils.configFromString;
import static org.junit.Assert.assertEquals;

public class JdbcConnectionTest {

	@Test
	public void testTransformSql() throws Exception {
		DBConfig dbc = new DBConfig("db1", configFromString("url = \"jdbc:h2:mem:db1\""));
		try (JdbcConnection c = new JdbcConnection(dbc)) {
			Handle h = DBI.open(c);
			assertEquals(asList("v1"), selectStringList(h, "select 'v1'"));
			assertEquals(asList("db1"), selectStringList(h, "select '${db_name}'"));
		}
	}

}