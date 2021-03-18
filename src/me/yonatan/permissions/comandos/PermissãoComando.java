package me.yonatan.permissions.comandos;

import java.util.HashMap;
import java.util.Map;

import me.yonatan.permissions.BungeMainPermissao;
import me.yonatan.permissions.objetos.PermissionManager;
import me.yonatan.permissions.objetos.UserPermission;

public class PermissãoComando implements IPermissãoComando {
	
	private Map<String, PermissãoSubComando> subcomandos = new HashMap<>();
	
	@Override
	public void onCommand(IPermissãoComandoSender sender, String... args) {
		PermissionManager manager = BungeMainPermissao.getManager();
		
		if(args.length == 0) {
			
			if(!(sender.isPlayer())) { 
				return;
			}
			
			UserPermission usuario = manager.getUsuario(sender.getName());
			
			sender.sendMessage("Você tem: "+ usuario.getGroupsNames().size());
		} else {
			String subname = args[0];
			if(subcomandos.containsKey(subname)) {
				subcomandos.get(subname).onCommand(sender, args);
			} else {
				sender.sendMessage("§cEste subcomando é inválido.");
			}
		}
	}
	
	public PermissãoComando() {
		subcomandos.put("setgrupo", new PermissionAddGroupComando());
	}

}
