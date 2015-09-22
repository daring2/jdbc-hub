package org.jdbchub.jdbc;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import java.sql.Connection;
import java.util.List;
import static org.jdbchub.JdbcHubTestUtils.allItems;
import static org.jdbchub.JdbcHubTestUtils.createTestDatabases;
import static org.junit.Assert.*;

public class JdbcConnectionTest {

	@Test
	public void testQuery() {
		List<Connection> cons = createTestDatabases();
		JdbcConnection c = new JdbcConnection(cons);
		try (Handle h = DBI.open(c)) {
			assertEquals(allItems(), h.select("select * from test_items"));
		}
	}

	@Test
	public void testClose() throws Exception {
		List<Connection> cons = createTestDatabases();
		try (JdbcConnection c = new JdbcConnection(cons)) {
			assertFalse(c.isClosed());

			cons.get(0).close();
			assertTrue(c.isClosed());
			assertFalse(c.map(Connection::isClosed).allMatch(r -> r));

			c.close();
			assertTrue(c.isClosed());
			assertTrue(c.map(Connection::isClosed).allMatch(r -> r));
		}
	}

}