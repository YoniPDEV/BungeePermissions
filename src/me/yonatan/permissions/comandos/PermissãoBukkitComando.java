package me.yonatan.permissions.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PermissãoBukkitComando implements CommandExecutor{
	private IPermissãoComando comando;
	
	public PermissãoBukkitComando(IPermissãoComando comando) {
        this.comando = comando;
    }
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		comando.onCommand(new PermissãoCommandSenderBukkit(sender), args);
		return false;
	}
}
