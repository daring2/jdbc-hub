package org.jdbchub.jdbc;

import org.jdbchub.config.DBConfig;
import org.junit.Test;
import java.sql.ResultSet;
import static org.jdbchub.JdbcHubTestUtils.newJdbcConnection;
import static org.jdbchub.config.ConfigUtils.configFromString;
import static org.junit.Assert.assertEquals;

public class JdbcConnectionTest {

	@Test
	public void testTransformSql() throws Exception {
		DBConfig dbc = new DBConfig("db1", configFromString("url = \"jdbc:h2:mem:db1\""));
		try (JdbcConnection c = newJdbcConnection(dbc)) {
			ResultSet rs = c.prepareStatement("select ${db_name}").executeQuery();
			rs.next();
			assertEquals("db1", rs.getString(1));
		}
	}

}