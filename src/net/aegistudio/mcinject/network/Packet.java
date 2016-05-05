package net.aegistudio.mcinject.network;

import net.aegistudio.reflect.clazz.Instance;

/**
 * This class delegates a really sendable
 * packet.
 * 
 * @author aegistudio
 */

public abstract class Packet<C extends Packet.Class> extends Instance<C> {
	public interface Class extends net.aegistudio.reflect.clazz.Class {};
	
	public final boolean isPlayOut;
	public Packet(C clazz, Object instance, boolean isPlayOut) {
		super(clazz, instance);
		this.isPlayOut = isPlayOut;
	}
	
	public boolean isPlayOut() {
		return this.isPlayOut;
	}
}