package org.jdbchub.config;

public enum ConfigPath {

	Root("jdbchub"),
	DriverClasses(Root.path + ".driverClasses"),
	ConfigLoaders(Root.path + ".configLoaders"),
	DbConfigs(Root.path + ".dbConfigs"),
	;

	public final String path;

	ConfigPath(String path) {
		this.path = path;
	}

}
