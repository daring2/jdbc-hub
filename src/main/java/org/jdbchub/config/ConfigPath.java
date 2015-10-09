package org.jdbchub.config;

public enum ConfigPath {

	Root("jdbchub"),
	ConfigLoaders(Root.path + ".configLoaders"),
	DbConfigs(Root.path + ".dbConfigs"),
	;

	public final String path;

	ConfigPath(String path) {
		this.path = path;
	}

}
