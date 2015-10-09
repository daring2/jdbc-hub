package org.jdbchub.config;

import com.typesafe.config.Config;
import static org.jdbchub.config.ConfigPath.DbConfigs;

public class TestConfigLoader implements ConfigLoader {

	@Override
	public Config load(Config mainConfig, Config loaderConfig) {
		String name = loaderConfig.getString("name");
		return loaderConfig.atPath(DbConfigs.path + "." + name);
	}

}
