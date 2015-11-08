package org.jdbchub.config;

public enum ConfigPath {

	Root("jdbchub"),
	DriverClasses(Root.path + ".driverClasses"),
	ConfigLoaders(Root.path + ".configLoaders"),
	SqlTransformers(Root.path + ".sqlTransformers"),
	DbConfigs(Root.path + ".dbConfigs"),
	;

	public final String path;

	ConfigPath(String path) {
		this.path = path;
	}

}
