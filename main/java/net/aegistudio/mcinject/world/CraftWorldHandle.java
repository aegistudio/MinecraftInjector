package net.aegistudio.mcinject.world;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.ProxiedClass;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.NamedExecutor;

public class CraftWorldHandle extends WorldHandle {
	
	public CraftWorldHandle(MinecraftServer server) throws Exception {
		AbstractClass craftworld = new SamePackageClass(server.getBukkitServerClass(), "CraftWorld");
		worldField = new NamedExecutor(craftworld.field(), "world");
		
		this.worldClass = new ProxiedClass<World.Class>(server, s -> new World.Class(s));
		this.blockPositionClass = new ProxiedClass<BlockPosition.Class>(server, s -> new BlockPosition.Class(server));
	}
}
