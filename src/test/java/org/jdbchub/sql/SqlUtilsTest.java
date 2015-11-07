package org.jdbchub.sql;

import org.junit.Test;
import static org.jdbchub.sql.SqlUtils.quoteSql;
import static org.junit.Assert.assertEquals;

public class SqlUtilsTest {

	@Test
	public void testQuoteSql() {
		assertEquals("'abc'", quoteSql("abc"));
		assertEquals("'a''b''c'", quoteSql("a'b'c"));
	}

}