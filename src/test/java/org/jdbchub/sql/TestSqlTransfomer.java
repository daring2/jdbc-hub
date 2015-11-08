package org.jdbchub.sql;

import com.typesafe.config.Config;
import org.jdbchub.config.DBConfig;

public class TestSqlTransfomer implements SqlTransformer {

	public final int index;
	public final String indexStr;

	public TestSqlTransfomer(int index) {
		this.index = index;
		this.indexStr = "" + index;
	}

	public TestSqlTransfomer(Config c) {
		this(c.getInt("index"));
	}

	@Override
	public boolean isEnabled(DBConfig dbc) {
		return dbc.name.endsWith(indexStr);
	}

	@Override
	public String transform(String sql) {
		return sql.replace("tr_index", indexStr)
			.replace("tr_prop" + index, "v" + index);
	}

}
