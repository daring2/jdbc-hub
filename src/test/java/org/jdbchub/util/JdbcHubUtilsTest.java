package org.jdbchub.util;

import org.jdbchub.config.DBConfig;
import org.jdbchub.jdbc.HubConnection;
import org.junit.Test;
import static org.jdbchub.JdbcHubTestUtils.allItems;
import static org.jdbchub.JdbcHubTestUtils.createTestConnection;
import static org.jdbchub.config.ConfigUtils.configFromString;
import static org.jdbchub.util.JdbcHubUtils.executeQuery;
import static org.junit.Assert.assertEquals;

public class JdbcHubUtilsTest {

	@Test
	public void testExecuteQuery() throws Exception {
		try (HubConnection c = createTestConnection()) {
			DBConfig dbc = new DBConfig("db1", configFromString("url = \"jdbc:h2:mem:db1\""));
			assertEquals(allItems().subList(0, 2), executeQuery(dbc, "select * from test_items"));
		}
	}

}