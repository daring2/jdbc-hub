package org.jdbchub.sql;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.jdbchub.config.DBConfig;
import java.util.HashMap;
import java.util.Map;
import static org.jdbchub.sql.SqlUtils.quoteSql;

public class DefaultSqlTransformer implements SqlTransformer {

	final DBConfig dbConfig;
	final StrSubstitutor substitutor;

	public DefaultSqlTransformer(DBConfig dbConfig) {
		this.dbConfig = dbConfig;
		this.substitutor = buildSubstitutor();
	}

	private StrSubstitutor buildSubstitutor() {
		Map<String, String> vars = new HashMap<>();
		vars.put("db_name", quoteSql(dbConfig.name));
		return new StrSubstitutor(vars);
	}

	@Override
	public boolean isEnabled(DBConfig dbc) {
		return true;
	}

	@Override
	public String transform(String sql) {
		return substitutor.replace(sql);
	}

}
