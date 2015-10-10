package org.jdbchub.config;

import com.google.common.collect.ImmutableMap;
import com.typesafe.config.Config;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import static org.jdbchub.config.ConfigPath.DbConfigs;
import static org.jdbchub.config.ConfigUtils.configFromMap;
import static org.jdbchub.config.ConfigUtils.emptyConfig;
import static org.junit.Assert.assertEquals;

public class JdbcConfigLoaderTest {

	@Test
	public void testLoad() {
		try (Handle h = DBI.open("jdbc:h2:mem:db0")) {
			h.execute("create table db_configs (name varchar(32), url varchar(128))");
			for (int i = 1; i <= 2; i++)
				h.insert("insert into db_configs values (?, ?)", "db" + i, "jdbc:h2:mem:db" + i);
			Config lc = configFromMap(ImmutableMap.of(
				"url", "jdbc:h2:mem:db0",
				"sql", "select name, url from db_configs"
			));
			Config dbc = new JdbcConfigLoader().load(emptyConfig(), lc).getConfig(DbConfigs.path);
			assertEquals(2, dbc.root().size());
			for (int i = 1; i <= 2; i++) {
				assertEquals("jdbc:h2:mem:db" + i, dbc.getString("db" + i + ".url"));
			}
		}
	}

}