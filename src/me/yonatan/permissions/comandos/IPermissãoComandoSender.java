package me.yonatan.permissions.comandos;

public interface IPermissãoComandoSender {
	
	void sendMessage(String name);
	boolean isPlayer();
	String getName();

}
