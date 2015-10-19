package org.jdbchub.config;

import com.typesafe.config.Config;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static org.jdbchub.config.ConfigPath.DbConfigs;
import static org.jdbchub.config.ConfigUtils.configFromMap;
import static org.jdbchub.util.JdbcHubUtils.executeQuery;

public class JdbcConfigLoader implements ConfigLoader {

	@Override
	public Config load(Config mainConfig, Config lc) {
		DBConfig dbc = new DBConfig("", lc);
		List<Map<String, Object>> rs = executeQuery(dbc, lc.getString("sql"));
		Map<String, Object> cm = rs.stream().collect(toMap(m -> "" + m.get("name"), m -> m));
		return configFromMap(cm).atPath(DbConfigs.path);
	}

}
