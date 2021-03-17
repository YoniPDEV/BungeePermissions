package me.yonatan.permissions;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.yonatan.permissions.api.ArmazenamentoPermissaoUsuarioBunge;
import me.yonatan.permissions.api.BukkitConfigs;
import me.yonatan.permissions.comandos.PermissãoBukkitComando;
import me.yonatan.permissions.comandos.PermissãoComando;
import me.yonatan.permissions.objetos.PermissionManager;
import me.yonatan.permissions.objetos.PermissionGroup;
import me.yonatan.permissions.objetos.UserPermission;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	private BukkitConfigs config;
	
	public static Main getInstance() {
        return instance;
    }
	
	@SuppressWarnings("unused")
	private static ArmazenamentoPermissaoUsuarioBunge armazenamentoUsuarios;
	
	private static PermissionManager manager;
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("§6[PluginPermissão] §fAtivado.");
		
		instance = this;
		
		Bukkit.getPluginCommand("permissão").setExecutor(new PermissãoBukkitComando(new PermissãoComando()));
		// BungeeCord.getInstance().getPluginManager().registerListener(this, new PermissaoListener());
		
		config = new BukkitConfigs("permissao.yml", this);
		
		manager = new PermissionManager();
		manager.setUsuariosArmazenamento(new ArmazenamentoPermissaoUsuarioBunge());
		armazenamentoUsuarios = new ArmazenamentoPermissaoUsuarioBunge();
		
		reload();
		reloadPlayers();
	}
	
	@Override
	public void onDisable() {
		save();
	}
	
	public void reload() {
		
		for(String grupoName : config.getKeys(false)) {
			
			PermissionGroup grupo = new PermissionGroup();
			ConfigurationSection secao = config.getSection(grupoName);
			grupo.setNome(secao.getString("nome"));
			manager.getGrupos().put(grupo.getNome().toLowerCase(), grupo);
			
		}
		
	}
	
	public void save() {
		for(PermissionGroup grupo : manager.getGrupos().values()) {
			String grupoNome = grupo.getNome();
			ConfigurationSection secao = config.create(grupoNome);
			secao.set("nome", grupo.getNome());
		}
		config.saveConfig();
	}
	
	public void saveUsuario(Player player){
        saveUsuario(manager.getUsuario(player.getName()));
    }
	
	public void saveUsuario(UserPermission usuario) {
		String nick = usuario.getPlayer().toLowerCase();
		BukkitConfigs playerConfig = new BukkitConfigs("jogadores/"+ nick+ ".yml");
		playerConfig.set("nome", usuario.getPlayer());
		playerConfig.set("permissoes", usuario.getPermissoes());
		playerConfig.set("grupos", usuario.getGroupsNames());
		playerConfig.saveConfig();
	}
	
	public void reloadPlayers(){
		manager.getUsuariosArmazenamento().loadAll();
	}

}
