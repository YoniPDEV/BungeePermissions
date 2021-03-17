package me.yonatan.permissions.comandos;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class PermissãoBungeComando extends Command{
	private IPermissãoComando comando;

	public PermissãoBungeComando(IPermissãoComando comando) {
		super("permissao", "permissao.admin", "minipex");
		this.comando = comando;
	}

	@Override
	public void execute(CommandSender sender, String[] args) {
		comando.onCommand(new PermissãoCommandSenderBungee(sender), args);
	}

}
