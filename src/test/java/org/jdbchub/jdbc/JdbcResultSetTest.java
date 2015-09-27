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
				.executeQuery("select value from test_items where name like '%1'");

			checkCursor(rs, BeforeFirst, 0);
			for (int i = 1; i <= 3; i++) {
				assertTrue(rs.next());
				checkRow(rs, i);
			}
			assertFalse(rs.next());
			checkCursor(rs, AfterLast, 0);
			for (int i = 3; i >= 1; i--) {
				assertTrue(rs.previous());
				checkRow(rs, i);
			}

			rs.afterLast();
			assertTrue(rs.previous());
			checkRow(rs, 3);

			rs.beforeFirst();
			assertTrue(rs.next());
			checkRow(rs, 1);

			//TODO check absolute nd relative
		}
	}

	public static void checkCursor(ResultSet rs, CursorPosition position, int row) throws Exception {
		assertEquals(position == BeforeFirst, rs.isBeforeFirst());
		assertEquals(position == First, rs.isFirst());
		assertEquals(position == Last, rs.isLast());
		assertEquals(position == AfterLast, rs.isAfterLast());
		assertEquals(row, rs.getRow());
	}

	public static void checkRow(ResultSet rs, int row) throws Exception {
		CursorPosition pos = row == 1 ? First : row == 3 ? Last : Middle;
		checkCursor(rs, pos, row);
		assertEquals("v" + row + "1", rs.getString(1));
	}

	enum CursorPosition {
		BeforeFirst, First, Middle, Last, AfterLast
	}

}