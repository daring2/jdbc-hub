package org.jdbchub;

import org.jdbchub.jdbc.HubConnection;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import java.sql.Connection;
import java.sql.SQLException;
import static java.util.Arrays.asList;
import static org.jdbchub.JdbcHubTestUtils.*;
import static org.junit.Assert.*;

public class JdbcHubDriverTest {

	@Test
	public void testAcceptsURL() throws SQLException {
		JdbcHubDriver driver = new JdbcHubDriver();
		assertTrue(driver.acceptsURL("jdbc:jdbchub:props"));
		assertFalse(driver.acceptsURL("jdbc:postgresql:props"));
	}

	@Test
	public void testConnect() throws SQLException {
		JdbcHubDriver driver = new JdbcHubDriver();
		String url = "jdbc:jdbchub:c1.conf";
		try (HubConnection hc = createTestConnection(); Connection c = driver.connect(url, null)) {
			Handle h = DBI.open(c);
			assertEquals(allItems(), h.select("select * from test_items"));
			assertEquals(asList("db1", "db2", "db3"), selectStringList(h, "select '${db_name}'"));

		}
	}

}