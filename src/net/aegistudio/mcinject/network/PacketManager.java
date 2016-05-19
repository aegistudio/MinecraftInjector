package net.aegistudio.mcinject.network;

import org.bukkit.map.MapView;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.ProxiedClass;
import net.aegistudio.mcinject.world.BlockPosition;
import net.aegistudio.mcinject.world.World;

public class PacketManager {
	public MinecraftServer server;
	public ProxiedClass<PacketPlayOutMap.Class> playOutMap;
	public ProxiedClass<PacketPlayOutBlockChange.Class> playOutBlockChange;
	public ProxiedClass<PacketPlayOutOpenSignEditor.Class> playOutOpenSignEditor;
	public ProxiedClass<PacketPlayOutUpdateSign.Class> playOutUpdateSign;
	public ProxiedClass<PacketPlayOutSetSlot.Class> playOutSetSlot;
	public ProxiedClass<PacketPlayInUseEntity.Class> playInUseEntity;
	
	public PacketManager(MinecraftServer server) {
		this.server = server;
		
		this.playOutBlockChange = new ProxiedClass<PacketPlayOutBlockChange.Class>(server, s -> new PacketPlayOutBlockChange.Class(server));
		this.playOutMap = new ProxiedClass<PacketPlayOutMap.Class>(server, s -> new PacketPlayOutMap.Class(s));
		this.playOutOpenSignEditor = new ProxiedClass<PacketPlayOutOpenSignEditor.Class>(server, s -> new PacketPlayOutOpenSignEditor.Class(s));
		this.playOutUpdateSign = new ProxiedClass<PacketPlayOutUpdateSign.Class>(server, s -> new PacketPlayOutUpdateSign.Class(s));
		this.playOutSetSlot = new ProxiedClass<PacketPlayOutSetSlot.Class>(server, s -> new PacketPlayOutSetSlot.Class(s));
		
		this.playInUseEntity = new ProxiedClass<PacketPlayInUseEntity.Class>(server, s -> new PacketPlayInUseEntity.Class(s));
	}
	
	public Packet<PacketPlayOutBlockChange.Class> playOutBlockChange(World world, BlockPosition position) {
		return new PacketPlayOutBlockChange(server, world, position);
	}

	public Packet<PacketPlayOutMap.Class> playOutMap(MapView mapView, byte[] raster, int columnOffset,
			int rowOffset, int columnLength, int rowLength) throws Exception {
		return new PacketPlayOutMap(server, mapView, raster, rowLength, rowLength, rowLength, rowLength);
	}
	
	public Packet<PacketPlayOutOpenSignEditor.Class> playOutOpenSignEditor(BlockPosition position) {
		return new PacketPlayOutOpenSignEditor(server, position);
	}

	public Packet<PacketPlayInUseEntity.Class> playInUseEntity(org.bukkit.entity.Entity entity) {
		return new PacketPlayInUseEntity(server, entity);
	}
}
