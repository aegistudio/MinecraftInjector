package net.aegistudio.mcinject.tileentity;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.ProxiedClass;

public class TileEntityManager {
	public ProxiedClass<TileEntity.Class> tileEntity;
	public ProxiedClass<TileEntitySign.Class> tileEntitySign;
	public ProxiedClass<TileEntityCommand.Class> tileEntityCommand;
	public ProxiedClass<CommandBlockListenerAbstract.Class> commandBlockListenerAbstract;
	
	public TileEntityManager(MinecraftServer server) {
		tileEntity = new ProxiedClass<TileEntity.Class>(server, s -> new TileEntitySign.SuperClass(s));
		tileEntitySign = new ProxiedClass<TileEntitySign.Class>(server, s -> new TileEntitySign.Class(s));
		tileEntityCommand = new ProxiedClass<TileEntityCommand.Class>(server, s -> new TileEntityCommand.Class(s));
		commandBlockListenerAbstract = new ProxiedClass<CommandBlockListenerAbstract.Class>(server, 
				s -> new CommandBlockListenerAbstract.Class(s));
	}
}
