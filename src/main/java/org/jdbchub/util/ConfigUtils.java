package org.jdbchub.util;

import com.typesafe.config.Config;

import java.util.Optional;

public class ConfigUtils {

	public static Optional<String> getOptString(Config c, String path) {
		return c.hasPath(path) ? Optional.of(c.getString(path)) : Optional.empty();
	}

	private ConfigUtils() {
	}
}
