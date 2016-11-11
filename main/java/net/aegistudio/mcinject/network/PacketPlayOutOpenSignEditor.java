package net.aegistudio.mcinject.network;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.world.BlockPosition;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.LengthedExecutor;

public class PacketPlayOutOpenSignEditor extends Packet<PacketPlayOutOpenSignEditor.Class> {
	public PacketPlayOutOpenSignEditor(Class clazz, Object instance) {
		super(clazz, instance, true);
	}
	
	public PacketPlayOutOpenSignEditor(MinecraftServer server, BlockPosition position) {
		super(server.getPacketManager().playOutOpenSignEditor.getClazz(), 
				server.getPacketManager().playOutOpenSignEditor.getClazz().newInstance(position), true);
	}

	public static class Class extends SamePackageClass implements Packet.Class {
		AbstractExecutor constructor;
		
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "PacketPlayOutOpenSignEditor");
			this.constructor = new LengthedExecutor(constructor(), 1);
		}
		
		public Object newInstance(BlockPosition location) {
			return constructor.invoke(null, location.thiz);
		}
	}
}
