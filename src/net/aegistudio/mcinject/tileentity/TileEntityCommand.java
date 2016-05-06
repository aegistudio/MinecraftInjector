package net.aegistudio.mcinject.tileentity;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.LengthedExecutor;
import net.aegistudio.reflect.method.NamedExecutor;

public class TileEntityCommand extends TileEntity<TileEntityCommand.Class>{
	public static class Class extends TileEntity.SubClass {
		AbstractExecutor constructor;
		AbstractExecutor getCommandBlock;
		
		public Class(MinecraftServer server) throws Exception {
			super(server, new SamePackageClass(server.getMinecraftServerClass(), "TileEntityCommand"));
			constructor = new LengthedExecutor(constructor(), 0);
			getCommandBlock = new NamedExecutor(method(), "getCommandBlock");
		}
		
		public Object newInstance() {
			return constructor.invoke(null);
		}
		
		public Object getCommandBlock(Object instance) {
			return getCommandBlock.invoke(instance);
		}
	}
	
	public TileEntityCommand(Class clazz, Object instance) {
		super(clazz, instance);
	}
	
	public TileEntityCommand(MinecraftServer server) {
		super(server.getTileEntityManager().tileEntityCommand.getClazz(), 
				server.getTileEntityManager().tileEntityCommand.getClazz().newInstance());
	}
	
	public CommandBlockListenerAbstract getCommandBlock() {
		return new CommandBlockListenerAbstract(clazz.server.getTileEntityManager()
				.commandBlockListenerAbstract.getClazz(), thiz);
	}
}
