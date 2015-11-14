package org.jdbchub.sql.dialect;

import com.typesafe.config.Config;
import org.jdbchub.sql.RegexSqlTransformer;
import org.jdbchub.sql.SqlTransformer;
import org.junit.Test;
import static org.jdbchub.JdbcHubTestUtils.dbConfig;
import static org.jdbchub.config.ConfigUtils.configFromResource;
import static org.junit.Assert.*;

public class OracleDialectTest {

	Config config = configFromResource("oracle-dialect.conf");

	@Test
	public void testAnyToOracle() {
		SqlTransformer tr = new RegexSqlTransformer(config.getConfig("anyToOracle"));

		assertTrue(tr.isEnabled(dbConfig("db1", "jdbc:h2:mem:db1")));
		assertFalse(tr.isEnabled(dbConfig("db1", "jdbc:oracle:thin:localhost:db1")));

		assertEquals("select 'v1'", tr.transform("select 'v1'"));
		assertEquals("select 'v2' from (select 'X' DUMMY) dual", tr.transform("select 'v2' from dual"));
	}

	@Test
	public void testMssqlToOracle() {
		SqlTransformer tr = new RegexSqlTransformer(config.getConfig("mssqlToOracle"));

		assertTrue(tr.isEnabled(dbConfig("db1", "jdbc:jtds:sqlserver://localhost:1433/db1")));
		assertFalse(tr.isEnabled(dbConfig("db1", "jdbc:h2:mem:db1")));

		assertEquals("select 'v1'", tr.transform("select 'v1'"));
		assertEquals("select 'v1' + 'v2'", tr.transform("select 'v1'||  'v2'"));
	}

}