package org.jdbchub.config;

import com.typesafe.config.Config;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.toMap;
import static org.jdbchub.config.ConfigPath.DbConfigs;
import static org.jdbchub.config.ConfigUtils.configFromMap;

public class JdbcConfigLoader implements ConfigLoader {

	@Override
	public Config load(Config mainConfig, Config lc) {
		DBConfig dbc = new DBConfig("", lc);
		try (Handle h = DBI.open(dbc.url, dbc.info)) {
			List<Map<String, Object>> rs = h.select(lc.getString("sql"));
			Map<String, Object> cm = rs.stream().collect(toMap(m -> "" + m.get("name"), m -> m));
			return configFromMap(cm).atPath(DbConfigs.path);
		}
	}

}
