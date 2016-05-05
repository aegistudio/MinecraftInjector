package net.aegistudio.mcinject.world;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.LengthedExecutor;
import net.aegistudio.reflect.method.MatchedExecutor;
import net.aegistudio.reflect.method.NamedExecutor;

public class CraftWorldShadow extends WorldManager {
	public CraftWorldShadow(MinecraftServer server) throws Exception {
		this.server = server;
		
		AbstractClass craftworld = new SamePackageClass(server.getBukkitServerClass(), "CraftWorld");
		worldField = new NamedExecutor(craftworld.field(), "world");
		
		AbstractClass minecraftWorld = new SamePackageClass(server.getMinecraftServerClass(), "World");
		
		getTileEntityMethod = new LengthedExecutor(minecraftWorld.method(), "getTileEntity", 1);
		setTileEntityMethod = new LengthedExecutor(minecraftWorld.method(), "setTileEntity", 2);
		
		newBlockPositionI = new MatchedExecutor(minecraftWorld.constructor(), int.class, int.class, int.class);
	}
}
