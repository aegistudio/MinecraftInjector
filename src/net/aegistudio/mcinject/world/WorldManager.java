package net.aegistudio.mcinject.world;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.method.AbstractExecutor;

public abstract class WorldManager {
	MinecraftServer server;
	AbstractExecutor worldField;
	AbstractExecutor getTileEntityMethod;
	AbstractExecutor setTileEntityMethod;
	
	AbstractExecutor newBlockPositionI;
	
	public World getHandle(org.bukkit.World world) {
		return new World(this.server, worldField.invoke(world));
	}
	
	public BlockPosition createBlockPosition(int x, int y, int z) {
		return new BlockPosition(this, newBlockPositionI.invoke(null, x, y, z));
	}
}
