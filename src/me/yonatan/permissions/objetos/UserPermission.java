package me.yonatan.permissions.objetos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import me.yonatan.permissions.api.BungeeConfigs;

public class UserPermission{
	
	private BungeeConfigs config;
	private String player;
	private Set<String> groupsNames = new HashSet<>();
	private Set<String> permissoes = new HashSet<>();
	private Map<String, Boolean> permissoesCalculadas = new HashMap<>();
	
	public Map<String, Boolean> getPermissoesCalculadas() {
		return permissoesCalculadas;
	}

	public void setPermissoesCalculadas(Map<String, Boolean> permissoesCalculadas) {
		this.permissoesCalculadas = permissoesCalculadas;
	}

	public boolean hasPermission(String permission) {
		if(permissoesCalculadas.containsKey("*")) {
			return true;
		}
		return permissoesCalculadas.getOrDefault(permission.toLowerCase(), false);
	}
	
	public void calcularPermissoes() {
		
		for(String permissao : permissoes) {
			
			if(permissao.startsWith("-")) {
				permissoesCalculadas.put(permissao.replaceFirst("-", ""), false);
			} else {
				permissoesCalculadas.put(permissao, true);
			}
			
		}
		
	}
	
	public Set<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<String> permissoes) {
		this.permissoes = permissoes;
	}


	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public Set<String> getGroupsNames() {
		return groupsNames;
	}

	public void setGroupsNames(Set<String> groupsNames) {
		this.groupsNames = groupsNames;
	}

	public void save() {
		
	}

	public void reload() {
		
	}

	public BungeeConfigs getConfig() {
		return config;
	}

	public void setConfig(BungeeConfigs config) {
		this.config = config;
	}

}
