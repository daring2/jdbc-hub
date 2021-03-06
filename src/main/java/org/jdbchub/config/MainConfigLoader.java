package org.jdbchub.config;

import com.typesafe.config.Config;
import java.util.List;
import static java.lang.String.format;
import static org.jdbchub.config.ConfigPath.ConfigLoaders;
import static org.jdbchub.config.ConfigPath.DriverClasses;
import static org.jdbchub.config.ConfigUtils.loadMainConfig;

public class MainConfigLoader {
	private final Config mainConfig;

	public MainConfigLoader(String file) {
		this.mainConfig = loadMainConfig(file);
	}
	
	public Config load() {
		loadDriverClasses();
		Config lcs = mainConfig.getConfig(ConfigLoaders.path);
		return lcs.root().keySet().stream()
			.map(n -> applyLoader(n, lcs.getConfig(n)))
			.reduce(mainConfig, Config::withFallback);
	}

	private void loadDriverClasses() {
		List<String> cls = mainConfig.getStringList(DriverClasses.path);
		cls.stream().forEach(this::loadDriverClass);
	}

	private void loadDriverClass(String cl) {
		try {
			Class.forName(cl);
		} catch (ClassNotFoundException e) {
			String msg = format("Cannot load JDBC driver '%s'", cl);
			throw new RuntimeException(msg, e);
		}
	}

	private Config applyLoader(String name, Config lc) {
		try {
			ConfigLoader loader = (ConfigLoader) Class.forName(lc.getString("class")).newInstance();
			return loader.load(mainConfig, lc);
		} catch (Exception e) {
			String msg = format("Cannot apply config loader '%s'", name);
			throw new RuntimeException(msg, e);
		}
	}

	
}
