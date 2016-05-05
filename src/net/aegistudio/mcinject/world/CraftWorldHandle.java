package net.aegistudio.mcinject.world;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.NamedExecutor;

public class CraftWorldHandle extends WorldHandle {
	
	public CraftWorldHandle(MinecraftServer server) throws Exception {
		AbstractClass craftworld = new SamePackageClass(server.getBukkitServerClass(), "CraftWorld");
		worldField = new NamedExecutor(craftworld.field(), "world");
		
		this.worldClass = new World.Class(server);
		this.blockPositionClass = new BlockPosition.Class(server);
	}
}
