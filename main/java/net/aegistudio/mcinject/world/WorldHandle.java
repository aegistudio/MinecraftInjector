package net.aegistudio.mcinject.world;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.ProxiedClass;
import net.aegistudio.reflect.method.AbstractExecutor;

public abstract class WorldHandle {
	MinecraftServer server;
	AbstractExecutor worldField;

	ProxiedClass<World.Class> worldClass;
	ProxiedClass<BlockPosition.Class> blockPositionClass;
	
	public Object getHandle(org.bukkit.World world) {
		return worldField.invoke(world);
	}
}
