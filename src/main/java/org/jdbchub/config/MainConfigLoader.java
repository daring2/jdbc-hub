package org.jdbchub.config;

import com.typesafe.config.Config;
import java.util.List;
import static org.jdbchub.config.ConfigUtils.getConfigList;
import static org.jdbchub.config.ConfigUtils.loadMainConfig;

public class MainConfigLoader {
	private final Config mainConfig;

	public MainConfigLoader(String file) {
		this.mainConfig = loadMainConfig(file);
	}
	
	public Config load() {
		List<Config> lcs = getConfigList(mainConfig, "jdbchub.configLoaders");
		return lcs.stream().reduce(mainConfig, (rc, lc) -> callLoader(lc).withFallback(rc));
	}

	private Config callLoader(Config lc) {
		try {
			ConfigLoader loader = (ConfigLoader) Class.forName(lc.getString("class")).newInstance();
			return loader.load(mainConfig, lc);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
}
