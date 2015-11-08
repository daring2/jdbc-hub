package org.jdbchub.util;

import org.jdbchub.config.DBConfig;
import org.jdbchub.jdbc.HubConnection;
import org.junit.Test;
import static org.jdbchub.JdbcHubTestUtils.*;
import static org.jdbchub.util.JdbcHubUtils.executeQuery;
import static org.junit.Assert.assertEquals;

public class JdbcHubUtilsTest {

	@Test
	public void testExecuteQuery() throws Exception {
		try (HubConnection c = createTestConnection()) {
			DBConfig dbc = dbConfig("db1", "jdbc:h2:mem:db1");
			assertEquals(allItems().subList(0, 2), executeQuery(dbc, "select * from test_items"));
		}
	}

}