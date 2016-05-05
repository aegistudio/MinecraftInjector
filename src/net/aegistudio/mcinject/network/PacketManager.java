package net.aegistudio.mcinject.network;

import org.bukkit.map.MapView;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.world.BlockPosition;
import net.aegistudio.mcinject.world.World;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.Invocable;
import net.aegistudio.reflect.method.LengthedExecutor;
import net.aegistudio.reflect.method.MatchedExecutor;
import net.aegistudio.reflect.method.ThisExecutor;

public class PacketManager {
	public PacketManager(MinecraftServer server) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		AbstractClass poBlockChange = new SamePackageClass(server.getMinecraftServerClass(), "PacketPlayOutBlockChange");
		this.playOutBlockChange = new LengthedExecutor(poBlockChange.constructor(), 2);
		
		AbstractClass poSignEditor = new SamePackageClass(server.getMinecraftServerClass(), "PacketPlayOutOpenSignEditor");
		this.playOutOpenSignEditor = new LengthedExecutor(poSignEditor.constructor(), 1);
		
		AbstractClass piUseEntity = new SamePackageClass(server.getMinecraftServerClass(), "PacketPlayInUseEntity");
		this.playInUseEntity = new LengthedExecutor(piUseEntity.constructor(), 0);
		this.playInUseEntity_entityId = new MatchedExecutor(piUseEntity.field(), int.class);
		
		AbstractClass poMap = new SamePackageClass(server.getMinecraftServerClass(), "PlayOutMap");
		for(Invocable constructor : poMap.constructor())
			for(PacketPoMapHandler.EnumHandler handler : PacketPoMapHandler.EnumHandler.values())
				if(handler.paramCount == constructor.getParameterList().length) {
					this.playOutMap = new ThisExecutor(constructor);
					this.playOutMapHandler = handler;
					break;
				}
		if(this.playOutMap == null) throw new NoSuchMethodException();
	}
	
	AbstractExecutor playOutBlockChange;
	public Packet playOutBlockChange(World world, BlockPosition position) {
		return new Packet(playOutBlockChange.invoke(null, world.thiz, position.thiz), true);
	}
	
	AbstractExecutor playOutMap;
	PacketPoMapHandler.EnumHandler playOutMapHandler;
	public Packet playOutMap(MapView mapView, byte[] raster, int columnOffset,
			int rowOffset, int columnLength, int rowLength) throws Exception {
		Object packet = playOutMapHandler.handler.playOutMap(playOutMap, mapView, raster, 
				columnOffset, rowOffset, columnLength, rowLength);
		return new Packet(packet, true);
	}
	
	AbstractExecutor playOutOpenSignEditor;
	public Packet playOutOpenSignEditor(Object tileEntitySign) {
		return new Packet(playOutBlockChange.invoke(null, tileEntitySign), true);
	}

	AbstractExecutor playInUseEntity;
	AbstractExecutor playInUseEntity_entityId;
	public Packet playInUseEntity(org.bukkit.entity.Entity entity) {
		Object packet = playInUseEntity.invoke(null);
		playInUseEntity_entityId.invoke(packet, entity.getEntityId());
		return new Packet(packet, false);
	}
}
