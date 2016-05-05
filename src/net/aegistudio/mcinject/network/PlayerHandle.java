package net.aegistudio.mcinject.network;

import org.bukkit.entity.Player;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.ProxiedClass;
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
	
	public PlayerConnection getHandle(Player player) {
		Object handle = getHandle.invoke(player);
		Object connection = this.playerConnection.invoke(handle);
		return new PlayerConnection(server, connection);
	}
}
