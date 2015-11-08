package org.jdbchub.jdbc;

import org.jdbchub.config.DBConfig;
import org.junit.Test;
import java.sql.ResultSet;
import static org.jdbchub.JdbcHubTestUtils.dbConfig;
import static org.jdbchub.JdbcHubTestUtils.newJdbcConnection;
import static org.junit.Assert.assertEquals;

public class JdbcStatementTest {

	@Test
	public void testTransformSql() throws Exception {
		DBConfig dbc = dbConfig("db1", "jdbc:h2:mem:db1");
		try (JdbcConnection c = newJdbcConnection(dbc)) {
			ResultSet rs = c.createStatement().executeQuery("select ${db_name}");
			rs.next();
			assertEquals("db1", rs.getString(1));
		}
	}
}