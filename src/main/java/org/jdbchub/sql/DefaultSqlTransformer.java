package org.jdbchub.sql;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.jdbchub.config.DBConfig;
import java.util.HashMap;
import java.util.Map;

public class DefaultSqlTransformer implements SqlTransformer {

	private final DBConfig dbConfig;
	private final StrSubstitutor substitutor;

	public DefaultSqlTransformer(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
		this.substitutor = buildSubstitutor();
	}

	private StrSubstitutor buildSubstitutor() {
		Map<String, String> vars = new HashMap<>();
		vars.put("db_name", dbConfig.name);
		return new StrSubstitutor(vars);
	}

	@Override
	public String transform(String sql) {
		return substitutor.replace(sql);
	}

}
