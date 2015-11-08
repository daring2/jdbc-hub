package org.jdbchub.sql.dialect;

import com.typesafe.config.Config;
import org.jdbchub.config.DBConfig;
import org.jdbchub.sql.RegexSqlTransformer;
import org.jdbchub.sql.SqlTransformer;

public class AnyToOracleTransformer implements SqlTransformer {

	final Config config;
	final RegexSqlTransformer regexTransformer;

	public AnyToOracleTransformer(Config config) {
		this.config = config;
		this.regexTransformer = new RegexSqlTransformer(config);
	}

	@Override
	public boolean isEnabled(DBConfig dbc) {
		return !dbc.url.startsWith("jdbc:oracle:");
	}

	@Override
	public String transform(String sql) {
		return regexTransformer.transform(sql);
	}
}
