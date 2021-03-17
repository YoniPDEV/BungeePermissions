package me.yonatan.permissions.api;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import me.yonatan.permissions.BungeMainPermissao;
import me.yonatan.permissions.objetos.UserPermission;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ArmazenamentoPermissaoUsuarioBunge implements Armazenamento<UserPermission, BungeeConfigs>{
	private HashMap<String, UserPermission> cacheamento = new HashMap<>();
	
	@Override
	public void save(UserPermission usuario) {
			BungeeConfigs config = getBy(usuario.getPlayer());
			config.set("nome", usuario.getPlayer());
			String[] array = usuario.getPermissoes().toArray(new String[usuario.getPermissoes().size()]);
			String[] array2 = usuario.getGroupsNames().toArray(new String[usuario.getGroupsNames().size()]);
			config.set("permissoes", array);
			config.set("grupos", array2);
			config.saveConfig();
	}
	

	@Override
	public UserPermission load(String key) {
		UserPermission usuario = new UserPermission();
		
		usuario.setPlayer(key);
		
		reload(usuario);
		cache().put(key.toLowerCase(), usuario);
		
		return usuario;
	}

	@Override
	public void reload(UserPermission dado) {
		BungeeConfigs config = getBy(dado.getPlayer());
		if(config.getKeys().isEmpty()) {
			return;
		}
		dado.setPlayer(config.getString("nome"));
		dado.setPermissoes(new HashSet<String>(config.getStringList("permissoes")));
		dado.setGroupsNames(new HashSet<String>(config.getStringList("grupos")));
	}

	@Override
	public Map<String, UserPermission> cache() {
		
		return cacheamento;
	}

	@Override
	public BungeeConfigs getBy(String key) {
		if(key == null) {
			
		}
		
		return new BungeeConfigs("jogadores/"+key.toLowerCase()+".yml", BungeMainPermissao.getInstance());
	}

	@Override
	public Collection<UserPermission> loadAll() {
		cache().clear();
		File pasta = new File(BungeMainPermissao.getInstance().getDataFolder(), "jogadores/");
		pasta.mkdir();
		for(String fileName : pasta.list()) {
			String key = fileName.replace(".yml", "");
			load(key);
		}
		
		return cacheamento.values();
	}

	@Override
	public UserPermission loadOrGet(String key) {
		if(cache().containsKey(key.toLowerCase())) {
			return cache().get(key.toLowerCase());
		}
		
		return load(key);
	}

}
