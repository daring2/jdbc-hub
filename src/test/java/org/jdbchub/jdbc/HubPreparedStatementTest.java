package org.jdbchub.jdbc;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.util.StringMapper;
import java.io.ByteArrayInputStream;
import java.io.StringReader;
import java.sql.PreparedStatement;
import static java.util.Arrays.asList;
import static org.jdbchub.JdbcHubTestUtils.createTestConnection;
import static org.junit.Assert.assertEquals;

public class HubPreparedStatementTest {

	@Test
	public void testExecuteUpdate() throws Exception {
		try (HubConnection c = createTestConnection()) {
			PreparedStatement st = c.prepareStatement("update test_items set value = ? where name like ?");
			st.setString(1, "nv1");
			st.setString(2, "%1");
			assertEquals(3, st.executeUpdate());
			st.setString(2, "%2_");
			assertEquals(2, st.executeUpdate());
		}
	}

	@Test
	public void testSetStream() throws Exception {
		try (HubConnection c = createTestConnection()) {
			Handle h = DBI.open(c);
			PreparedStatement st = c.prepareStatement("update test_items set value = ? where name like '%1'");
			st.setAsciiStream(1, new ByteArrayInputStream("nv1".getBytes()));
			assertEquals(3, st.executeUpdate());
			Query<String> q = h.createQuery("select value from test_items where name like '%1'").map(StringMapper.FIRST);
			assertEquals(asList("nv1", "nv1", "nv1"), q.list());
			st.setCharacterStream(1, new StringReader("nv2"));
			assertEquals(3, st.executeUpdate());
			assertEquals(asList("nv2", "nv2", "nv2"), q.list());
		}
	}

}