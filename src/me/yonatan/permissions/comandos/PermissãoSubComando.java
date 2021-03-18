package me.yonatan.permissions.comandos;

import java.util.ArrayList;

public class PermissãoSubComando{
	
	private String nome;
	private String permission;
	private ArrayList<String> aliase = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	
	public PermissãoSubComando(String nome, String permission) {
		super();
		this.nome = nome;
		this.permission = permission;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public ArrayList<String> getAliase() {
		return aliase;
	}
	public void setAliase(ArrayList<String> aliase) {
		this.aliase = aliase;
	}
	
	public void onCommand(IPermissãoComandoSender sender, String... args) {
		
	}

}
