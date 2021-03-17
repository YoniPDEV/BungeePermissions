package me.yonatan.permissions.objetos;

import java.util.HashMap;
import java.util.Map;

import me.yonatan.permissions.api.Armazenamento;

public class PermissionManager {
	
	public PermissionManager() {
		gruposPadroes();
	}
	
	public void gruposPadroes() {
		PermissionGroup membro = new PermissionGroup();
		
		membro.setNome("Membro");
		membro.setPrefix("§2[Membro] §7");
		membro.setSuffix("");
		membro.getPermissoes().add("comando.warp");
		grupos.put(membro.getNome().toLowerCase(), membro);
		
	}
	
	public boolean temGrupo(String groupname) {
		return grupos.containsKey(groupname.toLowerCase());
	}
	
	public PermissionGroup getGroup(String groupname) {
		return grupos.get(groupname.toLowerCase());
	}

	private Map<String, PermissionGroup> grupos = new HashMap<>();
	public Map<String, PermissionGroup> getGrupos() {
		return grupos;
	}

	public void setGrupos(Map<String, PermissionGroup> grupos) {
		this.grupos = grupos;
	}

	private Armazenamento<UserPermission, ?> usuariosArmazenamento;

	public Armazenamento<UserPermission, ?> getUsuariosArmazenamento() {
		return usuariosArmazenamento;
	}

	public void setUsuariosArmazenamento(Armazenamento<UserPermission, ?> usuariosArmazenamento) {
		this.usuariosArmazenamento = usuariosArmazenamento;
	}

	public UserPermission getUsuario(String player) {
        return usuariosArmazenamento.loadOrGet(player);
    }
	
}
