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
	public void testQuery() throws Exception {
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
			assertFalse(c.allMatch(Connection::isClosed));

			c.close();
			assertTrue(c.isClosed());
			assertTrue(c.allMatch(Connection::isClosed));
		}
	}

	@Test
	public void testIsValid() throws Exception {
		List<Connection> cons = createTestDatabases();
		try (JdbcConnection c = new JdbcConnection(cons)) {
			assertTrue(c.isValid(1));
			cons.get(0).close();
			assertFalse(c.isValid(1));
		}
	}

}