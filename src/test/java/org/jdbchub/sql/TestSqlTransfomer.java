package org.jdbchub.sql;

import com.typesafe.config.Config;
import org.jdbchub.config.DBConfig;

public class TestSqlTransfomer implements SqlTransformer {

	public final String index;

	public TestSqlTransfomer(Config c) {
		this.index = c.getString("index");
	}

	@Override
	public boolean isEnabled(DBConfig dbc) {
		return dbc.name.endsWith(index);
	}

	@Override
	public String transform(String sql) {
		return sql.replace("tr_index", index)
			.replace("tr_key" + index, "tr_value" + index);
	}

}
