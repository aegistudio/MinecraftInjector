package net.aegistudio.reflect.clazz;

import net.aegistudio.reflect.method.Invocable;

public interface Class {
	public java.lang.Class<?> getClazz();
	
	public String getPackage();
	
	public Invocable[] method();
	
	public Invocable[] constructor();
	
	public Invocable[] field();
	
	public boolean isInstance(Object instance);
}
