package net.aegistudio.mcinject.world;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.tileentity.TileEntity;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.Instance;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.LengthedExecutor;

/**
 * Please new this clazz directly!
 * @author aegistudio
 */

public class World extends Instance<World.Class>{
	public static class Class extends SamePackageClass {
		AbstractExecutor getTileEntityMethod;
		AbstractExecutor setTileEntityMethod;
		
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "World");
			
			AbstractClass minecraftWorld = new SamePackageClass(server.getMinecraftServerClass(), "World");
			getTileEntityMethod = new LengthedExecutor(minecraftWorld.method(), 1, "getTileEntity");
			setTileEntityMethod = new LengthedExecutor(minecraftWorld.method(), 2, "setTileEntity");
		}
	}

	public World(MinecraftServer server, Object world) {
		super(server.getWorldManager().worldClass.getClazz(), world);
	}

	public World(MinecraftServer server, org.bukkit.World bukkitWorld) {
		this(server, server.getWorldManager().getHandle(bukkitWorld));
	}
	
	@SuppressWarnings("unchecked")
	public <T extends TileEntity.Class> TileEntity<T> getTileEntity(BlockPosition blockPosition, T cast) {
		Object entityObject = clazz.getTileEntityMethod.invoke(thiz, blockPosition.thiz);
		if(entityObject == null) return null;
		return (TileEntity<T>) cast.newInstance(entityObject);
	}

	public void setTileEntity(BlockPosition blockPosition, TileEntity<?> tileEntity) {
		clazz.setTileEntityMethod.invoke(thiz, blockPosition.thiz, 
				tileEntity == null? null : tileEntity.thiz);
	}
}
