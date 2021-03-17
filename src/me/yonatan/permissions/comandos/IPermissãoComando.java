package me.yonatan.permissions.comandos;

public interface IPermissãoComando {
	
	void onCommand(IPermissãoComandoSender sender, String... args);

}
