package org.jdbchub.jdbc;

import org.jdbchub.config.DBConfig;
import org.junit.Test;
import java.sql.ResultSet;
import static org.jdbchub.config.ConfigUtils.configFromString;
import static org.junit.Assert.assertEquals;

public class JdbcStatementTest {

	@Test
	public void testTransformSql() throws Exception {
		DBConfig dbc = new DBConfig("db1", configFromString("url = \"jdbc:h2:mem:db1\""));
		try (JdbcConnection c = new JdbcConnection(dbc)) {
			ResultSet rs = c.createStatement().executeQuery("select ${db_name}");
			rs.next();
			assertEquals("db1", rs.getString(1));
		}
	}
}