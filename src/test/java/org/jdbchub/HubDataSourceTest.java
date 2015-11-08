package org.jdbchub;

import org.jdbchub.config.DBConfig;
import org.jdbchub.jdbc.HubConnection;
import org.jdbchub.sql.SqlTransformer;
import org.jdbchub.sql.TestSqlTransfomer;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import java.util.List;
import static java.util.Arrays.asList;
import static org.jdbchub.JdbcHubTestUtils.*;
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
	public void testBuildSqlTransformers() {
		HubDataSource d = new HubDataSource(loadMainConfig("c1.conf"));
		List<SqlTransformer> trs = d.sqlTransformers;
		assertEquals(2, trs.size());
		for (int i = 1; i <= 2; i++) {
			TestSqlTransfomer tr = (TestSqlTransfomer) trs.get(i - 1);
			assertEquals("db" + i, tr.dbName);
		}
	}

	@Test
	public void testGetConnection() throws Exception {
		try (HubConnection c = createTestConnection()) {
			HubDataSource d = new HubDataSource(loadMainConfig("c1.conf"));
			try (Handle h = DBI.open(d.getConnection())) {
				assertEquals(allItems(), h.select("select * from test_items"));
				assertEquals(asList("db1", "db2", "db3"), selectStringList(h, "select ${db_name}"));
			}
		}
	}

}