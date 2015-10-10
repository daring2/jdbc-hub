package org.jdbchub.config;

import com.typesafe.config.Config;
import org.junit.Test;
import java.util.HashSet;
import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;
import static org.jdbchub.config.ConfigPath.*;
import static org.junit.Assert.assertTrue;

public class MainConfigLoaderTest {

	static boolean testDriverLoaded;

	@Test
	public void testConfigLoaders() {
		testDriverLoaded = false;
		Config mc = new MainConfigLoader("c2.conf").load();
		assertTrue(testDriverLoaded);
		Config dbc = mc.getConfig(DbConfigs.path);
		assertEquals(3, dbc.root().size());
		HashSet<String> dbs = newHashSet("db1", "db2", "db3");
		for (String db : dbs) {
			assertEquals("jdbc:h2:mem:" + db, dbc.getString(db + ".url"));
		}
	}

}