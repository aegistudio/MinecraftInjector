package net.aegistudio.mcinject.network;

import org.bukkit.Location;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.world.BlockPosition;
import net.aegistudio.mcinject.world.World;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.LengthedExecutor;

public class PacketPlayOutBlockChange extends Packet<PacketPlayOutBlockChange.Class>{
	public static class Class extends SamePackageClass implements Packet.Class {
		AbstractExecutor playOutBlockChange;
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "PacketPlayOutBlockChange");
			this.playOutBlockChange = new LengthedExecutor(constructor(), 2);
		}

		public Object newInstance(Object world, Object blockPosition) {
			return this.playOutBlockChange.invoke(null, world, blockPosition);
		}
	}
	
	public PacketPlayOutBlockChange(MinecraftServer server, Object instance) {
		super(server.getPacketManager().playOutBlockChange, instance, true);
	}
	
	public PacketPlayOutBlockChange(MinecraftServer server, World world, BlockPosition blockPosition) {
		this(server, server.getPacketManager().playOutBlockChange.newInstance(world.thiz, blockPosition.thiz));
	}
	
	public PacketPlayOutBlockChange(MinecraftServer server, Location blockLocation) {
		this(server, server.getPacketManager().playOutBlockChange
				.newInstance(new World(server, blockLocation).thiz, 
						new BlockPosition(server, blockLocation).thiz));
	}
}
