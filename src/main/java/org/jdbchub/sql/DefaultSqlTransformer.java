package org.jdbchub.sql;

import org.apache.commons.lang3.text.StrSubstitutor;
import org.jdbchub.config.DBConfig;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.jdbchub.sql.SqlUtils.quoteSql;

public class DefaultSqlTransformer implements SqlTransformer {

	final DBConfig dbConfig;
	final List<SqlTransformer> sqlTransformers;
	final StrSubstitutor substitutor;

	public DefaultSqlTransformer(DBConfig dbConfig, List<SqlTransformer> sqlTransformers) {
		this.dbConfig = dbConfig;
		this.sqlTransformers = sqlTransformers;
		this.substitutor = buildSubstitutor();
	}

	private StrSubstitutor buildSubstitutor() {
		Map<String, String> vars = new HashMap<>();
		vars.put("db_name", quoteSql(dbConfig.name));
		vars.put("db_url", quoteSql(dbConfig.url));
		return new StrSubstitutor(vars);
	}

	@Override
	public boolean isEnabled(DBConfig dbc) {
		return true;
	}

	@Override
	public String transform(String sql) {
		sql = substitutor.replace(sql);
		for (SqlTransformer tr : sqlTransformers)
			sql = tr.transform(sql);
		return sql;
	}

}
