package net.aegistudio.mcinject.network;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.LengthedExecutor;
import net.aegistudio.reflect.method.MatchedExecutor;

public class PacketPlayInUseEntity extends Packet<PacketPlayInUseEntity.Class>{
	public static class Class extends SamePackageClass implements Packet.Class {
		AbstractExecutor playOutBlockChange;
		AbstractExecutor entityId;
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "PacketPlayInUseEntity");
			this.playOutBlockChange = new LengthedExecutor(constructor(), 0);
			this.entityId = new MatchedExecutor(field(), int.class);
		}

		public Object newInstance(org.bukkit.entity.Entity entity) {
			Object packet = this.playOutBlockChange.invoke(null);
			this.entityId.invoke(packet, entity.getEntityId());
			return packet;
		}
	}
	
	public PacketPlayInUseEntity(PacketPlayInUseEntity.Class clazz, Object instance) {
		super(clazz, instance, false);
	}
	
	public PacketPlayInUseEntity(MinecraftServer server, org.bukkit.entity.Entity entity) {
		this(server.getPacketManager().playInUseEntity.getClazz(), 
				server.getPacketManager().playInUseEntity.getClazz().newInstance(entity));
	}
}
