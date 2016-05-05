package net.aegistudio.mcinject.tileentity;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.ProxiedClass;

public class TileEntityManager {
	ProxiedClass<TileEntitySign.Class> tileEntitySign;
	public TileEntityManager(MinecraftServer server) {
		tileEntitySign = new ProxiedClass<TileEntitySign.Class>(server, s -> new TileEntitySign.Class(s));
	}
}
