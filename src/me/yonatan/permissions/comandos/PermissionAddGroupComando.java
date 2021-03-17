package me.yonatan.permissions.comandos;

import me.yonatan.permissions.BungeMainPermissao;
import me.yonatan.permissions.objetos.PermissionManager;
import me.yonatan.permissions.objetos.UserPermission;

public class PermissionAddGroupComando extends PermissãoSubComando{

	
	
	public PermissionAddGroupComando() {
		super("addgroup", "permissão.addgroup");
	}
	
	@Override
	public void onCommand(IPermissãoComandoSender sender, String... args) {
		if(args.length < 2) {
			sender.sendMessage("§cUse: /perm setgroup <nick> <grupo>");
			return;
		}
		
		String groupName = args[2];
		String playerName = args[1];
		PermissionManager manager = BungeMainPermissao.getManager();
		
		UserPermission conta = manager.getUsuario(playerName);
		
		if(!manager.temGrupo(groupName)) {
			sender.sendMessage("§cVocê escreveu um grupo inválido.");
			return;
		}
		
		conta.getGroupsNames().add(groupName);
		sender.sendMessage("§aGrupo adicionado ao player com sucesso.");
		
	}

}
