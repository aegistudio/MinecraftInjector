package net.aegistudio.mcinject.network;

import net.aegistudio.reflect.clazz.ThisClass;

public class PlayerConnection {
	public final PlayerManager manager;
	public final Object connection;
	public PlayerConnection(PlayerManager manager, Object connection) {
		this.manager = manager;
		this.connection = connection;
	}
	
	public void sendPacket(Packet packet) {
		if(!packet.isPlayOut) throw new IllegalArgumentException("Use loopback for play in.");
		this.manager.sendPacket.invoke(connection, packet.getPacket());
	}
	
	public void loopBack(Packet packet) {
		if(packet.isPlayOut) throw new IllegalArgumentException("Use sendPacket for play out.");
		this.manager.loopbackMethod.get(new ThisClass(packet.packet)).invoke(connection, packet.packet);
	}
}
