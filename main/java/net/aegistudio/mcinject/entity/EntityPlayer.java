package net.aegistudio.mcinject.entity;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.Instance;
import net.aegistudio.reflect.clazz.SamePackageClass;

public class EntityPlayer extends Instance<EntityPlayer.Class>{
	public static class Class extends SamePackageClass {
		public Class(MinecraftServer server) throws ClassNotFoundException {
			super(server.getMinecraftServerClass(), "EntityPlayer");
		}
	}
	
	public EntityPlayer(Class clazz, Object instance) throws IllegalArgumentException {
		super(clazz, instance);
	}
}
