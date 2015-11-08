package org.jdbchub.sql;

import com.typesafe.config.Config;
import org.jdbchub.config.DBConfig;

public class TestSqlTransfomer implements SqlTransformer {

	public final String dbName;

	public TestSqlTransfomer(Config c) {
		this.dbName = c.getString("dbName");
	}

	@Override
	public boolean isEnabled(DBConfig dbc) {
		return dbc.name.equals(dbName);
	}

	@Override
	public String transform(String sql) {
		return sql.replace("tr_db_name", dbName);
	}

}
