package org.jdbchub;

import org.jdbchub.jdbc.JdbcConnection;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import java.util.List;
import static org.jdbchub.JdbcHubTestUtils.allItems;
import static org.jdbchub.JdbcHubTestUtils.createTestConnection;
import static org.jdbchub.util.ConfigUtils.loadConfig;
import static org.junit.Assert.assertEquals;

public class JdbcDataSourceTest {

	@Test
	public void testBuildDBConfigs() {
		JdbcDataSource d = new JdbcDataSource(loadConfig("c1.conf"));
		List<DBConfig> dbs = d.dbConfigs;
		assertEquals(3, dbs.size());
		for (int i = 1; i <= 3; i++) {
			assertEquals("db" + i, dbs.get(i - 1).name);
			assertEquals("jdbc:h2:mem:db" + i, dbs.get(i - 1).url);
		}
	}

	@Test
	public void testGetConnection() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			JdbcDataSource d = new JdbcDataSource(loadConfig("c1.conf"));
			try (Handle h = DBI.open(d.getConnection())) {
				assertEquals(allItems(), h.select("select * from test_items"));
			}
		}
	}

}