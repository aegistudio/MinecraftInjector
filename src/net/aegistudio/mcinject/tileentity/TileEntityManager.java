package net.aegistudio.mcinject.tileentity;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.ProxiedClass;

public class TileEntityManager {
	ProxiedClass<TileEntity.Class> tileEntity;
	ProxiedClass<TileEntitySign.Class> tileEntitySign;
	ProxiedClass<TileEntityCommand.Class> tileEntityCommand;
	
	public TileEntityManager(MinecraftServer server) {
		tileEntity = new ProxiedClass<TileEntity.Class>(server, s -> new TileEntitySign.SuperClass(s));
		tileEntitySign = new ProxiedClass<TileEntitySign.Class>(server, s -> new TileEntitySign.Class(s));
		tileEntityCommand = new ProxiedClass<TileEntityCommand.Class>(server, s -> new TileEntityCommand.Class(s));
	}
}
