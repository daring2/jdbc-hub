package org.jdbchub.jdbc;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.IntegerMapper;
import java.sql.Statement;
import java.util.List;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.jdbchub.JdbcHubTestUtils.createTestConnection;
import static org.junit.Assert.*;

public class JdbcStatementTest {

	@Test
	public void testExecuteUpdate() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			Handle h = DBI.open(c);
			Statement st = c.createStatement();
			assertEquals(3, st.executeUpdate(updateValueSql("nv1", "%1")));
			assertEquals(asList(1, 1, 1), selectCountByValue(h, "nv1"));
			assertEquals(2, st.executeUpdate(updateValueSql("nv2", "%2_")));
			assertEquals(asList(0, 2, 0), selectCountByValue(h, "nv2"));
			assertEquals(0, st.executeUpdate(updateValueSql("nv3", "%3")));
			assertEquals(asList(0, 0, 0), selectCountByValue(h, "nv3"));
		}
	}

	@Test
	public void testBatch() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			Statement st = c.createStatement();
			st.addBatch(updateValueSql("nv1", "%1"));
			st.addBatch(updateValueSql("nv2", "%22"));
			assertArrayEquals(new int[]{3, 1}, st.executeBatch());
			Handle h = DBI.open(c);
			assertEquals(asList(1, 1, 1), selectCountByValue(h, "nv1"));
			assertEquals(asList(0, 1, 0), selectCountByValue(h, "nv2"));
		}
	}

	@Test
	public void testGetResultSet() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			Statement st = c.createStatement();
			assertNull(st.getResultSet());
			st.executeQuery("select 1");
			assertNotNull(st.getResultSet());
		}
	}

	@Test
	public void testGetUpdateCount() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			Statement st = c.createStatement();
			assertEquals(0, st.getUpdateCount());
			st.executeUpdate(updateValueSql("nv1", "%1"));
			assertEquals(3, st.getUpdateCount());
			st.executeQuery("select count(*) from test_items");
			assertEquals(-1, st.getUpdateCount());
		}
	}

	public String updateValueSql(String newValue, String nameExp) {
		return format("update test_items set value = '%s' where name like '%s'", newValue, nameExp);
	}

	public List<Integer> selectCountByValue(Handle h, String value) {
		return h.createQuery("select count(*) from test_items where value = ?")
				.bind(0, value).map(IntegerMapper.FIRST).list();
	}

}