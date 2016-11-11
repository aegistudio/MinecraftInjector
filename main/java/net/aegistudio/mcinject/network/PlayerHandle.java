package net.aegistudio.mcinject.network;

import org.bukkit.entity.Player;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.ProxiedClass;
import net.aegistudio.mcinject.entity.EntityPlayer;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.NamedExecutor;

public class PlayerHandle {
	MinecraftServer server;
	AbstractExecutor getHandle;
	AbstractExecutor playerConnection;
	
	ProxiedClass<PlayerConnection.Class> playerConnectionClass;
	
	public PlayerHandle(MinecraftServer server) throws Exception {
		this.server = server;
		
		AbstractClass craftPlayer = new SamePackageClass(server.getBukkitServerClass(), "entity.CraftPlayer");
		getHandle = new NamedExecutor(craftPlayer.method(), "getHandle");
		
		AbstractClass entityPlayer = new SamePackageClass(server.getMinecraftServerClass(), "EntityPlayer");
		playerConnection = new NamedExecutor(entityPlayer.field(), "playerConnection");

		this.playerConnectionClass = new ProxiedClass<PlayerConnection.Class>(server, s -> new PlayerConnection.Class(s));
	}
	
	public EntityPlayer getHandle(Player player) {
		Object handle = getHandle.invoke(player);
		EntityPlayer.Class clazz = server.getEntityManager().entityPlayer.getClazz();
		return new EntityPlayer(clazz, handle);
	}
	
	public Object getConnectionDirectly(Player player) {
		Object handle = getHandle.invoke(player);
		Object connection = playerConnection.invoke(handle);
		return connection;
	}
	
	public PlayerConnection getConnection(Player player) {
		Object handle = getHandle(player).thiz;
		Object connection = this.playerConnection.invoke(handle);
		return new PlayerConnection(server, connection);
	}
}
