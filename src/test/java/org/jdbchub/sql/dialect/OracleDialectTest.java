package org.jdbchub.sql.dialect;

import com.typesafe.config.Config;
import org.jdbchub.sql.RegexSqlTransformer;
import org.jdbchub.sql.SqlTransformer;
import org.junit.Test;
import static org.jdbchub.JdbcHubTestUtils.dbConfig;
import static org.jdbchub.config.ConfigUtils.configFromResource;
import static org.junit.Assert.*;

public class OracleDialectTest {

	Config config = configFromResource("oracle-dialect.conf").getConfig("anyToOracle");

	@Test
	public void testIsEnabled() {
		SqlTransformer tr = new RegexSqlTransformer(config);
		assertTrue(tr.isEnabled(dbConfig("db1", "jdbc:h2:mem:db1")));
		assertFalse(tr.isEnabled(dbConfig("db1", "jdbc:oracle:thin:localhost:db1")));
	}

	@Test
	public void testTransform() {
		SqlTransformer tr = new RegexSqlTransformer(config);
		assertEquals("select 'v1'", tr.transform("select 'v1'"));
		assertEquals("select 'v2' from (select 'X' DUMMY) dual", tr.transform("select 'v2' from dual"));
	}

}