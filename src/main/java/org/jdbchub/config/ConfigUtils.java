package org.jdbchub.config;

import com.typesafe.config.Config;

import java.io.File;
import java.util.Optional;
import static com.typesafe.config.ConfigFactory.*;

public class ConfigUtils {

	public static Optional<String> getOptString(Config c, String path) {
		return c.hasPath(path) ? Optional.of(c.getString(path)) : Optional.empty();
	}

	public static Config loadConfig(String file) {
		return defaultOverrides()
			.withFallback(parseFile(new File(file)))
			.withFallback(parseResources(file))
			.withFallback(defaultReference())
			.resolve();
	}

	private ConfigUtils() {
	}
}
