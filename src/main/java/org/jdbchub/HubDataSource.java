package org.jdbchub;

import com.typesafe.config.Config;
import org.jdbchub.config.DBConfig;
import org.jdbchub.jdbc.HubConnection;
import org.jdbchub.jdbc.JdbcConnection;
import org.jdbchub.sql.DefaultSqlTransformer;
import org.jdbchub.sql.SqlTransformer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.jdbchub.config.ConfigPath.DbConfigs;
import static org.jdbchub.config.ConfigPath.SqlTransformers;
import static org.jdbchub.jdbc.JdbcFunction.jdbcFunc;
import static org.jdbchub.util.JdbcHubUtils.filter;

public class HubDataSource {
	final Config config;
	final List<SqlTransformer> sqlTransformers;
	final List<DBConfig> dbConfigs;

	public HubDataSource(Config config) {
		this.config = config;
		this.sqlTransformers = buildSqlTransformers();
		this.dbConfigs = buildDBConfigs();
	}

	private List<DBConfig> buildDBConfigs() {
		Config c = this.config.getConfig(DbConfigs.path);
		Config dc = this.config.getConfig(DbConfigs.path + ".default");
		return c.root().keySet().stream().sorted()
			.filter(n -> !n.equals("default"))
			.map(n -> new DBConfig(n, c.getConfig(n).withFallback(dc)))
			.collect(toList());
	}

	private List<SqlTransformer> buildSqlTransformers() {
		Config c = this.config.getConfig(SqlTransformers.path);
		return c.root().keySet().stream()
			.map(n -> createSqlTransformer(n, c.getConfig(n)))
			.collect(toList());
	}

	private SqlTransformer createSqlTransformer(String name, Config c) {
		try {
			Class<?> cl = Class.forName(c.getString("class"));
			return (SqlTransformer) cl.getConstructor(Config.class).newInstance(c);
		} catch (Exception e) {
			String msg = format("Cannot create SqlTransformer '%s'", name);
			throw new RuntimeException(msg, e);
		}
	}

	public Connection getConnection() throws SQLException {
		List<Connection> cons = dbConfigs.parallelStream()
			.map(jdbcFunc(this::createConnection))
			.collect(toList());
		return new HubConnection(cons);
	}

	private Connection createConnection(DBConfig dbc) throws SQLException {
		try {
			List<SqlTransformer> trs = filter(sqlTransformers, tr -> tr.isEnabled(dbc));
			return new JdbcConnection(dbc, new DefaultSqlTransformer(dbc, trs));
		} catch (SQLException e) {
			throw new SQLException("Cannot connect to " + dbc.name, e);
		}
	}

}
