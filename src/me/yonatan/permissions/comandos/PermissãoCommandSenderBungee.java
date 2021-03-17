package me.yonatan.permissions.comandos;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class PermissãoCommandSenderBungee implements IPermissãoComandoSender {

	private CommandSender sender;
	
	public PermissãoCommandSenderBungee(CommandSender sender) {
        this.sender = sender;
    }
	
	@Override
	public void sendMessage(String message) {
		sender.sendMessage(new TextComponent(message));
	}

	@Override
	public boolean isPlayer() {
		
		return sender instanceof ProxiedPlayer;
	}

	@Override
	public String getName() {
		return sender.getName();
	}

}
