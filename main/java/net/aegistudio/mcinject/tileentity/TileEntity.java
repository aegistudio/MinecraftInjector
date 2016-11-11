package net.aegistudio.mcinject.tileentity;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.world.BlockPosition;
import net.aegistudio.mcinject.world.World;
import net.aegistudio.reflect.clazz.DelegateClass;
import net.aegistudio.reflect.clazz.Instance;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.NamedExecutor;

public class TileEntity<T extends TileEntity.Class> extends Instance<T>{
	public TileEntity(T clazz, Object instance) {
		super(clazz, instance);
	}
	
	public static interface Class extends net.aegistudio.reflect.clazz.Class {
		public World getWorld(Object thiz);
		public void setWorld(Object thiz, World world);
		
		public BlockPosition getPosition(Object thiz);
		public void setPosition(Object thiz, BlockPosition pos);
		
		public TileEntity<?> newInstance(Object instance);
	};
	
	public static class SuperClass extends SamePackageClass implements Class {
		AbstractExecutor world;
		AbstractExecutor position;
		MinecraftServer server;
		public SuperClass(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "TileEntity");
			world = new NamedExecutor(field(), "world");
			position = new NamedExecutor(field(), "position");
			this.server = server;
		}
		
		public World getWorld(Object thiz) {	return new World(server, world.invoke(thiz));	}
		public void setWorld(Object thiz, World world) {	this.world.invoke(thiz, world.thiz);		}
		
		public BlockPosition getPosition(Object thiz) {	return new BlockPosition(server, position.invoke(thiz));		}
		public void setPosition(Object thiz, BlockPosition pos) {		position.invoke(thiz, pos.thiz);		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public TileEntity<?> newInstance(Object instance) {
			return new TileEntity(this, instance);
		}
	}
	
	public static abstract class SubClass extends DelegateClass implements Class {
		protected MinecraftServer server;
		protected TileEntity.Class clazz;
		public SubClass(MinecraftServer server, net.aegistudio.reflect.clazz.Class clazz) {
			super(clazz);
			this.clazz = server.getTileEntityManager().tileEntity.getClazz();
			this.server = server;
		}
		
		public World getWorld(Object thiz) {	return clazz.getWorld(thiz);	}
		public void setWorld(Object thiz, World world) {	clazz.setWorld(thiz, world);	}

		public BlockPosition getPosition(Object thiz) {		return clazz.getPosition(thiz);	}
		public void setPosition(Object thiz, BlockPosition pos) {	clazz.setPosition(thiz, pos);	}
	}
	
	public World getWorld() {	return clazz.getWorld(thiz);	}
	public void setWorld(World world) { clazz.setWorld(thiz, world);	}
	public BlockPosition getPosition() { return clazz.getPosition(thiz);	}
	public void setPosition(BlockPosition pos) { clazz.setPosition(thiz, pos);	}
}
