package me.yonatan.permissions;

import me.yonatan.permissions.objetos.UserPermission;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PermissionCheckEvent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PermissaoListener implements Listener{
	
	@EventHandler
	public void saida(PlayerDisconnectEvent e) {
		UserPermission usuario = BungeMainPermissao.getManager().getUsuario(e.getPlayer().getName());
		BungeMainPermissao.getArmazenamentoUsuarios().save(usuario);
	}
	
	@EventHandler
	public void checkPermission(PermissionCheckEvent e) {
		String key = e.getSender().getName();
		UserPermission usuario = BungeMainPermissao.getManager().getUsuario(key);
		e.setHasPermission(usuario.hasPermission(e.getPermission()));
	}
	
	@EventHandler
    public void join(ServerConnectedEvent e){
        String key = e.getPlayer().getName();
        UserPermission usuario = BungeMainPermissao.getManager().getUsuario(key);
        usuario.getPermissoesCalculadas().put("*", true);
        usuario.getPermissoes().add("*");

        if (e.getPlayer().hasPermission("permissao.admin")){
            e.getPlayer().sendMessage(new TextComponent("§aVocê tem a permissão"));
        } else{
            e.getPlayer().sendMessage(new TextComponent("§cVocê não tem esta permissão"));
        }
        e.getPlayer().sendMessage(new TextComponent(""+BungeMainPermissao.getManager().
                getUsuariosArmazenamento().cache()));
    }

}
