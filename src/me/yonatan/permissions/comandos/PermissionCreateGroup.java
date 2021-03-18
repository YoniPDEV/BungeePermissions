package me.yonatan.permissions.comandos;

import me.yonatan.permissions.BungeMainPermissao;
import me.yonatan.permissions.objetos.PermissionGroup;
import me.yonatan.permissions.objetos.PermissionManager;

public class PermissionCreateGroup extends PermissãoSubComando{
	
	public PermissionCreateGroup() {
		super("addgroup", "permissão.creategroup");
	}
	
	@Override
	public void onCommand(IPermissãoComandoSender sender, String... args) {
		if(args.length < 3) {
			sender.sendMessage("§cUse: /perm creategroup <nome> <prefix> <suffix>");
			return;
		}
		
		String nome = args[1];
		String prefix = args[2];
		String suffix = args[3];
		
		PermissionManager manager = BungeMainPermissao.getManager();
		
		if(manager.temGrupo(nome)) {
			sender.sendMessage("§cUm grupo com este nome já está criado em nosso banco de dados.");
			return;
		}
		
		PermissionGroup grupo = new PermissionGroup();
		
		grupo.setNome(nome);
		grupo.setPrefix(prefix);
		grupo.setSuffix(suffix);
		sender.sendMessage("§aGrupo "+nome+" criado com sucesso!");
		
	}

}
