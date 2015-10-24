package org.jdbchub;

import com.typesafe.config.Config;
import org.jdbchub.config.DBConfig;
import org.jdbchub.jdbc.HubConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import static org.jdbchub.config.ConfigPath.DbConfigs;
import static org.jdbchub.jdbc.JdbcFunction.jdbcFunc;

public class HubDataSource {
	final Config config;
	final List<DBConfig> dbConfigs;

	public HubDataSource(Config config) {
		this.config = config;
		this.dbConfigs = buildDBConfigs();
	}

	private List<DBConfig> buildDBConfigs() {
		Config c = this.config.getConfig(DbConfigs.path);
		Config dc = this.config.getConfig(DbConfigs.path + ".default");
		return c.root().keySet().stream().sorted()
			.filter(n -> !n.equals("default"))
			.map(n -> new DBConfig(n, c.getConfig(n).withFallback(dc)))
			.collect(Collectors.toList());
	}

	public Connection getConnection() throws SQLException {
		List<Connection> cons = dbConfigs.parallelStream()
			.map(jdbcFunc(this::createConnection))
			.collect(Collectors.toList());
		return new HubConnection(cons);
	}

	private Connection createConnection(DBConfig dbc) throws SQLException {
		try {
			return DriverManager.getConnection(dbc.url, dbc.info);
		} catch (SQLException e) {
			throw new SQLException("Cannot connect to " + dbc.name, e);
		}
	}

}
