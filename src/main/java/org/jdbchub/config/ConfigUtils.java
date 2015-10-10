package org.jdbchub.config;

import com.typesafe.config.Config;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static com.typesafe.config.ConfigFactory.*;
import static java.util.stream.Collectors.toList;

public class ConfigUtils {

	public static Config emptyConfig() {
		return empty();
	}

	public static Config configFromMap(Map<String, ?> map) {
		return parseMap(map);
	}

	public static Config configFromString(String str) {
		return parseString(str);
	}

	public static Config loadMainConfig(String file) {
		return defaultOverrides()
			.withFallback(parseFile(new File(file)))
			.withFallback(parseResources(file))
			.withFallback(defaultReference())
			.resolve();
	}

	public static Optional<String> getOptString(Config c, String path) {
		return c.hasPath(path) ? Optional.of(c.getString(path)) : Optional.empty();
	}

	@SuppressWarnings("unchecked")
	public static List<Config> getConfigList(Config c, String path) {
		Config pc = c.getConfig(path);
		return pc.root().keySet().stream().map(pc::getConfig).collect(toList());
	}

	private ConfigUtils() {
	}
}
