package me.yonatan.permissions;

import java.util.Map.Entry;

import me.yonatan.permissions.api.ArmazenamentoPermissaoUsuarioBunge;
import me.yonatan.permissions.api.BungeeConfigs;
import me.yonatan.permissions.comandos.PermissãoBungeComando;
import me.yonatan.permissions.comandos.PermissãoComando;
import me.yonatan.permissions.objetos.PermissionManager;
import me.yonatan.permissions.objetos.PermissionGroup;
import me.yonatan.permissions.objetos.UserPermission;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;

public class BungeMainPermissao extends Plugin{
	
	private static BungeMainPermissao instance;
	private static PermissionManager manager;
	private static ArmazenamentoPermissaoUsuarioBunge armazenamentoUsuarios;
	private BungeeConfigs config;
	
	public static BungeMainPermissao getInstance() {
        return instance;
    }
	
	public static ArmazenamentoPermissaoUsuarioBunge getArmazenamentoUsuarios() {
		return armazenamentoUsuarios;
	}

	public static void setArmazenamentoUsuarios(ArmazenamentoPermissaoUsuarioBunge armazenamentoUsuarios) {
		BungeMainPermissao.armazenamentoUsuarios = armazenamentoUsuarios;
	}
	
	public static PermissionManager getManager() {
		return manager;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		BungeeCord.getInstance().getConsole().
			sendMessage(new TextComponent("§a[PermissionsPlugin] Ativado com sucesso!"));
		
		BungeeCord.getInstance().getPluginManager().registerCommand(this, new PermissãoBungeComando(new PermissãoComando()));
		BungeeCord.getInstance().getPluginManager().registerListener(this, new PermissaoListener());
		
		config = new BungeeConfigs("permissao.yml", this);
		
		manager = new PermissionManager();
		manager.setUsuariosArmazenamento(new ArmazenamentoPermissaoUsuarioBunge());
		armazenamentoUsuarios = new ArmazenamentoPermissaoUsuarioBunge();
		
		reload();
		reloadPlayers();
	}

	@Override
	public void onDisable() {
		save();
		for(Entry<String, UserPermission> cache : getArmazenamentoUsuarios().cache().entrySet()) {
			getArmazenamentoUsuarios().save(cache.getValue());	
		}
	}
	
	public void reload() {
		
		for(String grupoName : config.getKeys()) {
			
			PermissionGroup grupo = new PermissionGroup();
			Configuration secao = config.getSection(grupoName);
			grupo.setNome(secao.getString("nome"));
			manager.getGrupos().put(grupo.getNome().toLowerCase(), grupo);
		}
	}
	
	public void save() {
		for(PermissionGroup grupo : manager.getGrupos().values()) {
			String grupoNome = grupo.getNome();
			Configuration secao = config.create(grupoNome);
			secao.set("nome", grupo.getNome());
			secao.set("Prefix", grupo.getPrefix());
			secao.set("suffix", grupo.getSuffix());
		}
		config.saveConfig();
	}
	
	public void reloadPlayers(){
		manager.getUsuariosArmazenamento().loadAll();
	}
	}
