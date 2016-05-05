package net.aegistudio.mcinject.network;

import java.util.HashMap;

import org.bukkit.entity.Player;

import net.aegistudio.mcinject.MinecraftServer;

import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.clazz.ThisClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.Invocable;
import net.aegistudio.reflect.method.NamedExecutor;
import net.aegistudio.reflect.method.ThisExecutor;

public class PlayerManager {
	AbstractExecutor getHandle;
	AbstractExecutor playerConnection;
	AbstractExecutor sendPacket;
	
	public HashMap<AbstractClass, AbstractExecutor> loopbackMethod = new HashMap<AbstractClass, AbstractExecutor>();
	
	public PlayerManager(MinecraftServer server) throws Exception {
		AbstractClass craftPlayer = new SamePackageClass(server.getBukkitServerClass(), "entity.CraftPlayer");
		getHandle = new NamedExecutor(craftPlayer.method(), "getHandle");
		
		AbstractClass entityPlayer = new SamePackageClass(server.getMinecraftServerClass(), "EntityPlayer");
		playerConnection = new NamedExecutor(entityPlayer.field(), "playerConnection");

		AbstractClass playerConnectionClazz = new SamePackageClass(server.getMinecraftServerClass(), "PlayerConnection");
		sendPacket = new NamedExecutor(playerConnectionClazz.method(), "sendPacket");
		
		for(Invocable method : playerConnectionClazz.method())
			loopbackMethod.put(new ThisClass(method.getParameterList()[0]), new ThisExecutor(method));
	}
	
	public PlayerConnection getConnection(Player player) {
		Object handle = getHandle.invoke(player);
		Object connection = this.playerConnection.invoke(handle);
		return new PlayerConnection(this, connection);
	}
}
