package org.jdbchub;

import com.typesafe.config.Config;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;
import static org.jdbchub.config.ConfigUtils.loadConfig;

public class JdbcHubDriver implements Driver {

	public static final String UrlPrefix = "jdbc:jdbchub:";

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return url != null && url.startsWith(UrlPrefix);
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		if (!acceptsURL(url))
			return null;
		Config c = loadConfig(url.substring(UrlPrefix.length()));
		return new JdbcDataSource(c).getConnection();
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		return new DriverPropertyInfo[0];
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return Logger.getLogger("org.jdbchub");
	}

	@Override
	public int getMajorVersion() {
		return 1;
	}

	@Override
	public int getMinorVersion() {
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		return false;
	}

}
