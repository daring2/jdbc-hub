package org.jdbchub.config;

import com.typesafe.config.Config;

public class TestConfigLoader implements ConfigLoader {

	@Override
	public Config load(Config mainConfig, Config loaderConfig) {
		String name = loaderConfig.getString("name");
		return loaderConfig.atPath("jdbchub.dbconfigs." + name);
	}

}
