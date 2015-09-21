package org.jdbchub.jdbc;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.sql.Connection;
import java.util.List;

import static org.jdbchub.JdbcHubTestUtils.allItems;
import static org.jdbchub.JdbcHubTestUtils.createTestDatabases;
import static org.junit.Assert.assertEquals;

public class JdbcConnectionTest {

	@Test
	public void testQuery() {
		List<Connection> cons = createTestDatabases();
		JdbcConnection c = new JdbcConnection(cons);
		try (Handle h = DBI.open(c)) {
			assertEquals(allItems(), h.select("select * from test_items"));
		}
	}

}