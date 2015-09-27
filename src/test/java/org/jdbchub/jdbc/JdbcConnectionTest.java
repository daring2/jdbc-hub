package org.jdbchub.jdbc;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.Update;
import org.skife.jdbi.v2.util.StringMapper;

import java.sql.Connection;
import java.sql.Savepoint;

import static java.util.Arrays.asList;
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

	@Test
	public void testSavepoint() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			Handle h = DBI.open(c);
			Update st = h.createStatement("update test_items set value = ? where name like '%1'");
			Query<String> q = h.createQuery("select value from test_items where name like '%1'").map(StringMapper.FIRST);
			h.begin();
			st.bind(0, "nv1").execute();
			assertEquals(asList("nv1", "nv1", "nv1"), q.list());
			Savepoint sp = c.setSavepoint();
			st.bind(0, "nv2").execute();
			assertEquals(asList("nv2", "nv2", "nv2"), q.list());
			c.rollback(sp);
			assertEquals(asList("nv1", "nv1", "nv1"), q.list());
		}
	}

}