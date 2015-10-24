package org.jdbchub.util;

import org.jdbchub.config.DBConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.DriverManager.getConnection;

public class JdbcHubUtils {

	public static List<Map<String, Object>> executeQuery(DBConfig dbc, String sql)  {
		try {
			try (Connection c = getConnection(dbc.url, dbc.info)) {
				return executeQuery(c, sql);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static List<Map<String, Object>> executeQuery(Connection c, String sql) throws SQLException {
		try (Statement st = c.createStatement()) {
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			List<String> cls = new ArrayList<>();
			for (int i = 1, size = md.getColumnCount(); i <= size; i++)
				cls.add(md.getColumnLabel(i));
			List<Map<String, Object>> result = new ArrayList<>();
			while (rs.next()) {
				Map<String, Object> row = new HashMap<>();
				for (String cl : cls)
					row.put(cl.toLowerCase(), rs.getObject(cl));
				result.add(row);
			}
			return result;
		}
	}

	private JdbcHubUtils() {
	}
}
