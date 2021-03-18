package me.yonatan.permissions.objetos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PermissionGroup{
	
	private String nome;
	private String prefix;
	private String suffix;
	private Set<String> subGroupsName = new HashSet<>();
	private Set<String> permissoes = new HashSet<>();
	private Map<String, Boolean> permissoesCalculadas = new HashMap<>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Set<String> getSubGroupsName() {
		return subGroupsName;
	}

	public void setSubGroupsName(Set<String> subGroupsName) {
		this.subGroupsName = subGroupsName;
	}

	public Set<String> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<String> permissoes) {
		this.permissoes = permissoes;
	}

	public Map<String, Boolean> getPermissoesCalculadas() {
		return permissoesCalculadas;
	}

	public void setPermissoesCalculadas(Map<String, Boolean> permissoesCalculadas) {
		this.permissoesCalculadas = permissoesCalculadas;
	}
	
	public void calcularPermissoes() {
		
		for(String permissao : permissoes) {
			
			if(permissao.startsWith("-")) {
				permissoesCalculadas.put(permissao.replaceFirst("-", ""), false);
			} else {
				permissoesCalculadas.put(permissao, true);
			}
			
		}
		
	}

}
