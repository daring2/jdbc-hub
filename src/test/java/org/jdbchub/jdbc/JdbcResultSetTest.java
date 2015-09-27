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
			ResultSet rs = selectAll(c);

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
		}
	}

	@Test
	public void testAbsolute() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			ResultSet rs = selectAll(c);

			assertTrue(rs.absolute(2));
			checkRow(rs, 2);
			assertTrue(rs.absolute(5));
			checkRow(rs, 5);

			assertTrue(rs.absolute(-5));
			checkRow(rs, 2);
			assertTrue(rs.absolute(-2));
			checkRow(rs, 5);

			assertFalse(rs.absolute(0));
			checkCursor(rs, BeforeFirst, 0);
			assertFalse(rs.absolute(10));
			checkCursor(rs, AfterLast, 0);
		}
	}

	@Test
	public void testRelative() throws Exception {
		try (JdbcConnection c = createTestConnection()) {
			ResultSet rs = selectAll(c);

			assertTrue(rs.relative(2));
			checkRow(rs, 2);
			assertTrue(rs.relative(2));
			checkRow(rs, 4);
			assertTrue(rs.relative(-3));
			checkRow(rs, 1);

			assertFalse(rs.relative(-10));
			checkCursor(rs, BeforeFirst, 0);
			assertFalse(rs.relative(10));
			checkCursor(rs, AfterLast, 0);
		}
	}

	public static ResultSet selectAll(JdbcConnection c) throws Exception {
		return c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
			.executeQuery("select value from test_items order by name");
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