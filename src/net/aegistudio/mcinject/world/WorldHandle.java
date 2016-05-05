package net.aegistudio.mcinject.world;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.method.AbstractExecutor;

public abstract class WorldHandle {
	MinecraftServer server;
	AbstractExecutor worldField;

	World.WorldClass worldClass;
	BlockPosition.BlockPositionClass blockPositionClass;
	
	public World getHandle(org.bukkit.World world) {
		return new World(this.server, worldField.invoke(world));
	}
}
