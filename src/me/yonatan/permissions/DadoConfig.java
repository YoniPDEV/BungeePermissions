package me.yonatan.permissions;

import org.bukkit.configuration.ConfigurationSection;

public interface DadoConfig {
	
	public void save(ConfigurationSection section);
	void reload(ConfigurationSection section);

}
