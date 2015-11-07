package org.jdbchub.sql;

import static org.apache.commons.lang3.StringUtils.replace;

public class SqlUtils {

	public static String quoteSql(String str) {
		return "'" + replace(str, "'", "''") + "'";
	}

	private SqlUtils() {
	}
}
