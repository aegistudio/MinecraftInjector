package net.aegistudio.mcinject.network;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.SamePackageClass;

public class PacketPlayOutUpdateSign {
	public static class Class extends SamePackageClass implements Packet.Class{
		public Class(MinecraftServer server) throws ClassNotFoundException {
			super(server.getMinecraftServerClass(), "PacketPlayOutUpdateSign");
		}
	}
}
