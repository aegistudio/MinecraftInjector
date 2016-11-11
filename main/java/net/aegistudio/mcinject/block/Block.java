package net.aegistudio.mcinject.block;

import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.NamedExecutor;
import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.DelegateClass;
import net.aegistudio.reflect.clazz.Instance;

public class Block extends Instance<Block.Class> {
	public Block(Class clazz, Object instance) throws ClassCastException {
		super(clazz, instance);
	}

	public static interface Class extends net.aegistudio.reflect.clazz.Class {
		public int getId(Object instance);
	}
	
	public static class SuperClass extends SamePackageClass implements Class{
		AbstractExecutor getId;
		AbstractExecutor getBlockById;
		public SuperClass(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "Block");
			getId = new NamedExecutor(method(), "getId");
			getBlockById = new NamedExecutor(method(), "getById");
		}
		
		public Block getBlockById(int id) {
			return new Block(this, getBlockById.invoke(null, id));
		}
		
		@SuppressWarnings("deprecation")
		public Block getBlockByMaterial(org.bukkit.Material material) {
			return getBlockById(material.getId());
		}
		
		public int getId(Object instance) {
			return (int) getId.invoke(instance);
		}
	}
	
	public static abstract class SubClass extends DelegateClass implements Class {
		private final MinecraftServer server;
		protected SubClass(MinecraftServer server, net.aegistudio.reflect.clazz.Class clazz) {
			super(clazz);
			this.server = server;
		}
		
		public int getId(Object instance) {
			return server.getBlockClass().getId(instance);
		}
	}
	
	public int getId() {
		return clazz.getId(thiz);
	}
}
