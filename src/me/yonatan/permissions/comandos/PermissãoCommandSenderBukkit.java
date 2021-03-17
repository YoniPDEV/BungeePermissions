package me.yonatan.permissions.comandos;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissãoCommandSenderBukkit implements IPermissãoComandoSender{
	private CommandSender sender;
	
	public PermissãoCommandSenderBukkit(CommandSender sender) {
        this.sender = sender;
    }
	
	@Override
	public void sendMessage(String message) {
		sender.sendMessage(message);
	}

	@Override
	public boolean isPlayer() {
		
		return sender instanceof Player;
	}

	@Override
	public String getName() {
		
		return sender.getName();
	}

}
