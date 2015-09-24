package org.jdbchub.jdbc;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.sql.Connection;

import static org.jdbchub.JdbcHubTestUtils.allItems;
import static org.jdbchub.JdbcHubTestUtils.createTestConnection;
import static org.junit.Assert.*;

public class JdbcConnectionTest {

	@Test
	public void testQuery() throws Exception {
		JdbcConnection c = createTestConnection();
		try (Handle h = DBI.open(c)) {
			assertEquals(allItems(), h.select("select * from test_items"));
		}
	}

	@Test
	public void testClose() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			assertFalse(c.isClosed());

			c.entity(0).close();
			assertTrue(c.isClosed());
			assertFalse(c.allMatch(Connection::isClosed));

			c.close();
			assertTrue(c.isClosed());
			assertTrue(c.allMatch(Connection::isClosed));
		}
	}

	@Test
	public void testIsValid() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			assertTrue(c.isValid(1));
			c.entity(0).close();
			assertFalse(c.isValid(1));
		}
	}

}