package net.aegistudio.mcinject.tileentity;

import net.aegistudio.reflect.clazz.Instance;

public class TileEntity<T extends TileEntity.Class> extends Instance<T>{
	public TileEntity(T clazz, Object instance) {
		super(clazz, instance);
	}
	
	public static interface Class extends net.aegistudio.reflect.clazz.Class {};
}
