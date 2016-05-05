package net.aegistudio.mcinject.network;

/**
 * This class delegates a really sendable
 * packet.
 * 
 * @author aegistudio
 */

public class Packet {
	public Object packet;
	public boolean isPlayOut;
	public Packet(Object packet, boolean playOut) {
		this.packet = packet;
		this.isPlayOut = playOut;
	}
	
	public Object getPacket() {
		return this.packet;
	}
}