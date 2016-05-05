package net.aegistudio.mcinject.world;

import net.aegistudio.mcinject.MinecraftServer;

public class World {
	public final WorldManager access;
	public final Object world;
	public World(MinecraftServer server, Object world) {
		this.access = server.getWorldManager();
		this.world = world;
	}
	
	public Object getTileEntity(BlockPosition blockPosition) {
		return access.getTileEntityMethod.invoke(world, blockPosition.getBlockPosition());
	}
	
	public void setTileEntity(BlockPosition blockPosition, TileEntity tileEntity) {
		access.setTileEntityMethod.invoke(world, blockPosition.getBlockPosition(), tileEntity.tileEntity);
	}
}
