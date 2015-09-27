package org.jdbchub.jdbc;

import org.junit.Test;
import java.sql.ResultSet;
import static org.jdbchub.JdbcHubTestUtils.createTestConnection;
import static org.jdbchub.jdbc.JdbcResultSetTest.CursorPosition.*;
import static org.junit.Assert.*;

public class JdbcResultSetTest {

	@Test
	public void testCursor() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			ResultSet rs = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
				.executeQuery("select value from test_items order by name");

			checkCursor(rs, BeforeFirst, 0);
			for (int i = 1; i <= 6; i++) {
				assertTrue(rs.next());
				checkRow(rs, i);
			}
			assertFalse(rs.next());
			checkCursor(rs, AfterLast, 0);
			for (int i = 6; i >= 1; i--) {
				assertTrue(rs.previous());
				checkRow(rs, i);
			}

			rs.afterLast();
			assertTrue(rs.previous());
			checkRow(rs, 6);

			rs.beforeFirst();
			assertTrue(rs.next());
			checkRow(rs, 1);

			//TODO check absolute and relative
		}
	}

	public static void checkCursor(ResultSet rs, CursorPosition position, int row) throws Exception {
		assertEquals(position == BeforeFirst, rs.isBeforeFirst());
		assertEquals(position == First, rs.isFirst());
		assertEquals(position == Last, rs.isLast());
		assertEquals(position == AfterLast, rs.isAfterLast());
		assertEquals(row, rs.getRow());
	}

	public static void checkRow(ResultSet rs, int r) throws Exception {
		CursorPosition pos = r == 1 ? First : r == 6 ? Last : Middle;
		checkCursor(rs, pos, r);
		int i = (r - 1) / 2 + 1;
		int k = (r % 2) == 1 ? 1 : 2;
		assertEquals("v" + i + k, rs.getString(1));
	}

	enum CursorPosition {
		BeforeFirst, First, Middle, Last, AfterLast
	}

}