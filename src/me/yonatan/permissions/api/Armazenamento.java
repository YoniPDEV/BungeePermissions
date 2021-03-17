package me.yonatan.permissions.api;

import java.util.Collection;
import java.util.Map;

public interface Armazenamento<T, M> {

	
	M getBy(String key);

	Map<String, T> cache();
	
	void save(T dado);
	
	T load(String key);

	void reload(T dado);
	
	Collection<T> loadAll();
	
	T loadOrGet(String key);
	
}
