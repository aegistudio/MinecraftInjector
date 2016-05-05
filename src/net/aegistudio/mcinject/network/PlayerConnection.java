package net.aegistudio.mcinject.network;

import java.util.HashMap;

import org.bukkit.entity.Player;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.Instance;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.clazz.ThisClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.Invocable;
import net.aegistudio.reflect.method.NamedExecutor;
import net.aegistudio.reflect.method.ThisExecutor;

public class PlayerConnection extends Instance<PlayerConnection.Class>{
	public static class Class extends SamePackageClass {
		AbstractExecutor sendPacket;
		HashMap<AbstractClass, AbstractExecutor> loopbackMethod = new HashMap<AbstractClass, AbstractExecutor>();
		
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "PlayerConnection");
			sendPacket = new NamedExecutor(method(), "sendPacket");
			
			for(Invocable method : method())
				loopbackMethod.put(new ThisClass(method.getParameterList()[0]), new ThisExecutor(method));
		}
	}
	
	public PlayerConnection(MinecraftServer server, Object connection) {
		super(server.getPlayerManager().playerConnectionClass, connection);
	}
	
	public PlayerConnection(MinecraftServer server, Player player) {
		this(server, server.getPlayerManager().getHandle(player));
	}
	
	public void sendPacket(Packet<?> packet) {
		if(!packet.isPlayOut()) throw new IllegalArgumentException("Use loopback for play in.");
		clazz.sendPacket.invoke(thiz, packet.thiz);
	}
	
	public void loopBack(Packet<?> packet) {
		if(packet.isPlayOut()) throw new IllegalArgumentException("Use sendPacket for play out.");
		clazz.loopbackMethod.get(new ThisClass(packet.clazz)).invoke(thiz, packet.thiz);
	}
}
