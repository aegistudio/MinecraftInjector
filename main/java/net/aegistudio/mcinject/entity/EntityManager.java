package net.aegistudio.mcinject.entity;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.ProxiedClass;

public class EntityManager {
	public ProxiedClass<EntityPlayer.Class> entityPlayer;
	
	public EntityManager(MinecraftServer server) {
		this.entityPlayer = new ProxiedClass<EntityPlayer.Class>(server, s -> new EntityPlayer.Class(s));
	}
}
