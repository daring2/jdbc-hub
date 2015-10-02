package org.jdbchub;

import com.typesafe.config.Config;
import org.jdbchub.jdbc.JdbcConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import static org.jdbchub.jdbc.JdbcFunction.jdbcFunc;

public class JdbcDataSource {
	final Config config;
	final List<DBConfig> dbConfigs;

	public JdbcDataSource(Config config) {
		this.config = config;
		this.dbConfigs = buildDBConfigs();
	}

	private List<DBConfig> buildDBConfigs() {
		Config c = this.config.getConfig("jdbchub.dbconfigs");
		return c.root().keySet().stream().sorted().map(n ->
			new DBConfig(n, c.getConfig(n))
		).collect(Collectors.toList());
	}

	Connection getConnection() throws SQLException {
		List<Connection> cons = dbConfigs.parallelStream()
			.map(jdbcFunc(this::createConnection))
			.collect(Collectors.toList());
		return new JdbcConnection(cons);
	}

	private Connection createConnection(DBConfig dbc) throws SQLException {
		try {
			return DriverManager.getConnection(dbc.url, dbc.info);
		} catch (SQLException e) {
			throw new SQLException("Cannot connect to " + dbc.name, e);
		}
	}

}
