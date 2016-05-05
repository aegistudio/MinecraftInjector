package net.aegistudio.mcinject.world;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.Instance;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.MatchedExecutor;

/**
 * You could directly new this clazz!
 * 
 * @author aegistudio
 */

public class BlockPosition extends Instance<BlockPosition.Class> {
	public static class Class extends SamePackageClass {
		public AbstractExecutor newBlockPositionI;
		public Class(MinecraftServer server) throws Exception{
			super(server.getMinecraftServerClass(), "BlockPosition");
			newBlockPositionI = new MatchedExecutor(constructor(), int.class, int.class, int.class);
		}
		
		public Object newBlockPosition(int x, int y, int z) {
			return newBlockPositionI.invoke(null, x, y, z);
		}
	}
	
	public BlockPosition(MinecraftServer server, Object blockPosition) {
		super(server.getWorldManager().blockPositionClass, blockPosition);
	}
	
	public BlockPosition(MinecraftServer server, int x, int y, int z) {
		this(server, server.getWorldManager().blockPositionClass.newBlockPosition(x, y, z));
	}
}
