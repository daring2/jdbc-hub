package org.jdbchub.sql;

import org.jdbchub.config.DBConfig;

public interface SqlTransformer {
	boolean isEnabled(DBConfig dbc);
	String transform(String sql);
}
