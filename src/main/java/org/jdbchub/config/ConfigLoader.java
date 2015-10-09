package org.jdbchub.config;

import com.typesafe.config.Config;

public interface ConfigLoader {

	Config load(Config mainConfig, Config loaderConfig);

}
