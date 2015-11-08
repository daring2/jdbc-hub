package org.jdbchub;

import com.google.common.collect.ImmutableMap;
import org.jdbchub.config.DBConfig;
import org.jdbchub.jdbc.HubConnection;
import org.jdbchub.jdbc.JdbcConnection;
import org.jdbchub.sql.DefaultSqlTransformer;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.util.StringMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static java.util.Collections.emptyList;

public class JdbcHubTestUtils {

	public static HubConnection createTestConnection() {
		List<Connection> cons = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			Handle h = DBI.open("jdbc:h2:mem:db" + i);
			h.execute("create table test_items (name varchar(32), value varchar(32))");
			for (int k = 1; k <= 2; k++)
				h.insert("insert into test_items values (?, ?)", "n" + i + k, "v" + i + k);
			cons.add(h.getConnection());
		}
		return new HubConnection(cons);
	}

	public static List<Map<String, Object>> allItems() {
		List<Map<String, Object>> items = new ArrayList<>();
		for (int i = 1; i <= 3; i++) {
			for (int k = 1; k <= 2; k++)
				items.add(ImmutableMap.of("name", "n" + i + k, "value", "v" + i + k));
		}
		return items;
	}

	public static JdbcConnection newJdbcConnection(DBConfig dbc) throws SQLException {
		return new JdbcConnection(dbc, new DefaultSqlTransformer(dbc, emptyList()));
	}

	public static List<String> selectStringList(Handle h, String sql) {
		return h.createQuery(sql).map(StringMapper.FIRST).list();
	}

	private JdbcHubTestUtils() {
	}
}
