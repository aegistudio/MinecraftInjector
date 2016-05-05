package net.aegistudio.mcinject.network;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.world.BlockPosition;
import net.aegistudio.mcinject.world.World;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.LengthedExecutor;
import net.aegistudio.reflect.method.MatchedExecutor;

public class PacketManager {
	private final MinecraftServer server;
	public PacketManager(MinecraftServer server) throws ClassNotFoundException, NoSuchMethodException, SecurityException {
		this.server = server;
		
		AbstractClass poBlockChange = new SamePackageClass(server.getMinecraftServerClass(), "PacketPlayOutBlockChange");
		this.playOutBlockChange = new LengthedExecutor(poBlockChange.constructor(), 2);
		
		AbstractClass poSignEditor = new SamePackageClass(server.getMinecraftServerClass(), "PacketPlayOutOpenSignEditor");
		this.playOutOpenSignEditor = new LengthedExecutor(poSignEditor.constructor(), 1);
		
		AbstractClass piUseEntity = new SamePackageClass(server.getMinecraftServerClass(), "PacketPlayInUseEntity");
		this.playInUseEntity = new LengthedExecutor(piUseEntity.constructor(), 0);
		this.playInUseEntity_entityId = new MatchedExecutor(piUseEntity.field(), int.class);
	}
	
	AbstractExecutor playOutBlockChange;
	public Packet playOutBlockChange(World world, BlockPosition position) {
		return new Packet(playOutBlockChange.invoke(null, world.world, position.blockPosition), true);
	}
	
	public Packet playOutBlockChange(org.bukkit.World world, int x, int y, int z) {
		World worldInstance = server.getWorldManager().getHandle(world);
		BlockPosition position = server.getWorldManager().createBlockPosition(x, y, z);
		return this.playOutBlockChange(worldInstance, position);
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
