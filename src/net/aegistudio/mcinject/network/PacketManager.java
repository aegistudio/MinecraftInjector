package net.aegistudio.mcinject.network;

import org.bukkit.map.MapView;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.world.BlockPosition;
import net.aegistudio.mcinject.world.World;

public class PacketManager {
	public MinecraftServer server;
	public PacketPlayOutMap.Class playOutMap;
	public PacketPlayOutBlockChange.Class playOutBlockChange;
	
	public PacketPlayInUseEntity.Class playInUseEntity;
	
	public PacketManager(MinecraftServer server) throws Exception {
		this.server = server;

		//AbstractClass poSignEditor = new SamePackageClass(server.getMinecraftServerClass(), "PacketPlayOutOpenSignEditor");
		//this.playOutOpenSignEditor = new LengthedExecutor(poSignEditor.constructor(), 1);
		
		this.playOutBlockChange = new PacketPlayOutBlockChange.Class(server);
		this.playOutMap = new PacketPlayOutMap.Class(server);
		
		this.playInUseEntity = new PacketPlayInUseEntity.Class(server);
	}
	
	public Packet<PacketPlayOutBlockChange.Class> playOutBlockChange(World world, BlockPosition position) {
		return new PacketPlayOutBlockChange(server, world, position);
	}

	public Packet<PacketPlayOutMap.Class> playOutMap(MapView mapView, byte[] raster, int columnOffset,
			int rowOffset, int columnLength, int rowLength) throws Exception {
		return new PacketPlayOutMap(server, mapView, raster, rowLength, rowLength, rowLength, rowLength);
	}
	
	/*
	AbstractExecutor playOutOpenSignEditor;
	public Packet<?> playOutOpenSignEditor(Object tileEntitySign) {
		return new Packet(playOutOpenSignEditor.invoke(null, tileEntitySign), true);
	}
	*/

	public Packet<PacketPlayInUseEntity.Class> playInUseEntity(org.bukkit.entity.Entity entity) {
		return new PacketPlayInUseEntity(server, entity);
	}
}
