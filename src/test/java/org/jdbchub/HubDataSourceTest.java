package org.jdbchub;

import org.jdbchub.config.DBConfig;
import org.jdbchub.jdbc.HubConnection;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import java.util.List;
import static org.jdbchub.JdbcHubTestUtils.allItems;
import static org.jdbchub.JdbcHubTestUtils.createTestConnection;
import static org.jdbchub.config.ConfigUtils.loadMainConfig;
import static org.junit.Assert.assertEquals;

public class HubDataSourceTest {

	@Test
	public void testBuildDBConfigs() {
		HubDataSource d = new HubDataSource(loadMainConfig("c1.conf"));
		List<DBConfig> dbs = d.dbConfigs;
		assertEquals(3, dbs.size());
		for (int i = 1; i <= 3; i++) {
			DBConfig c = dbs.get(i - 1);
			assertEquals("db" + i, c.name);
			assertEquals("jdbc:h2:mem:db" + i, c.url);
		}
	}

	@Test
	public void testGetConnection() throws Exception {
		try (HubConnection c = createTestConnection()) {
			HubDataSource d = new HubDataSource(loadMainConfig("c1.conf"));
			try (Handle h = DBI.open(d.getConnection())) {
				assertEquals(allItems(), h.select("select * from test_items"));
			}
		}
	}

}