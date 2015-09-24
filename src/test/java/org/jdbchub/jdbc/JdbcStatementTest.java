package org.jdbchub.jdbc;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.util.IntegerMapper;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static java.util.Arrays.asList;
import static org.jdbchub.JdbcHubTestUtils.createTestDatabases;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class JdbcStatementTest {

	@Test
	public void testBatch() throws Exception {
		List<Connection> cons = createTestDatabases();
		try (JdbcConnection c = new JdbcConnection(cons)) {
			Statement st = c.createStatement();
			st.addBatch("update test_items set value = 'a' where name like '%1'");
			st.addBatch("update test_items set value = 'b' where name like '%22'");
			assertArrayEquals(new int[] {3, 1}, st.executeBatch());
			Handle h = DBI.open(c);
			Query<Integer> q = h.createQuery("select count(*) from test_items where value = ?").map(IntegerMapper.FIRST);
			assertEquals(asList(1, 1, 1), q.bind(0, "a").list());
			assertEquals(asList(0, 1, 0), q.bind(0, "b").list());
		}
	}

}