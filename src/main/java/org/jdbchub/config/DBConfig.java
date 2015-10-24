package org.jdbchub.config;

import com.typesafe.config.Config;
import java.util.Properties;
import static org.jdbchub.config.ConfigUtils.getOptString;

public class DBConfig {

	public final String name;
	public final String url;
	public final Properties info;

	public DBConfig(String name, Config c) {
		this.name = name;
		this.url = c.getString("url");
		this.info = buildInfo(c);
	}

	private Properties buildInfo(Config c) {
		Properties info = new Properties();
		getOptString(c, "user").ifPresent(v -> info.put("user", v));
		getOptString(c, "password").ifPresent(v -> info.put("password", v));
		return info;
	}

	@Override
	public String toString() {
		return "DBConfig{" +
			"name='" + name + '\'' +
			", url='" + url + '\'' +
			", info=" + info +
			'}';
	}

}
