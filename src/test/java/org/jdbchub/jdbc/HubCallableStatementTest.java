package org.jdbchub.jdbc;

import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.util.StringMapper;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.jdbchub.JdbcHubTestUtils.createTestConnection;
import static org.junit.Assert.assertEquals;

public class HubCallableStatementTest {

	@Test
	public void testCall() throws Exception {
		try (HubConnection c = createTestConnection()) {
			Handle h = DBI.open(c);
			h.execute("create alias test_function for \"org.jdbchub.jdbc.HubCallableStatementTest.testFunction\"");
			CallableStatement st = c.prepareCall("{call test_function(?, ?)}");
			st.setString(1, "nv1");
			st.setString(2, "%1");
			st.execute();
			Query<String> q = h.createQuery("select value from test_items where name like '%1'").map(StringMapper.FIRST);
			assertEquals(asList("nv1", "nv1", "nv1"), q.list());
		}
	}

	public static int testFunction(Connection c, String value, String nameExp) throws SQLException {
		String sql = format("update test_items set value = '%s' where name like '%s'", value, nameExp);
		return c.createStatement().executeUpdate(sql);
	}

}