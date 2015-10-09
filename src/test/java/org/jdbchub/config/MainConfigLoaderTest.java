package org.jdbchub.config;

import com.typesafe.config.Config;
import org.junit.Test;
import java.util.HashSet;
import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;

public class MainConfigLoaderTest {

	@Test
	public void testConfigLoaders() {
		Config mc = new MainConfigLoader("c2.conf").load();
		Config dbc = mc.getConfig("jdbchub.dbconfigs");
		assertEquals(3, dbc.root().size());
		HashSet<String> dbs = newHashSet("db1", "db2", "db3");
		for (String db : dbs) {
			assertEquals("jdbc:h2:mem:" + db, dbc.getString(db + ".url"));
		}
	}

}