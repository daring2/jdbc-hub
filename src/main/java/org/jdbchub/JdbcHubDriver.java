package org.jdbchub;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class JdbcHubDriver implements Driver {

    public static final String URL_PREFIX = "jdbc:jdbchub:";

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return url != null && url.startsWith(URL_PREFIX);
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        throw new UnsupportedOperationException();
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
