package org.jdbchub.sql;

import org.jdbchub.config.DBConfig;

public class DefaultSqlTransformer implements SqlTransformer {

	private final DBConfig dbConfig;

	public DefaultSqlTransformer(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
	}

	@Override
	public String transform(String sql) {
		//TODO optimize
		return sql.replace("${db_name}", dbConfig.name);
	}

}
