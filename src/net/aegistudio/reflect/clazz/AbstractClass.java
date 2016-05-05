package net.aegistudio.reflect.clazz;

import java.lang.reflect.Array;
import java.util.function.Function;

import net.aegistudio.reflect.method.ConstructorInvocable;
import net.aegistudio.reflect.method.FieldInvocable;
import net.aegistudio.reflect.method.Invocable;
import net.aegistudio.reflect.method.MethodInvocable;

public abstract class AbstractClass {
	public abstract Class<?> getClazz();
	
	public abstract String getPackage();
	
	public Invocable[] method() {
		return loadup(getClazz().getDeclaredMethods(), 
				Invocable.class, method -> new MethodInvocable(method));
	}
	
	public Invocable[] constructor() {
		return loadup(getClazz().getDeclaredConstructors(), 
				Invocable.class, constructor -> new ConstructorInvocable(constructor));
	}
	
	public boolean isInstance(Object instance) {
		return getClazz().isInstance(instance);
	}
	
	public Invocable[] field() {
		return loadup(getClazz().getDeclaredFields(), 
				Invocable.class, field -> new FieldInvocable(field));
	}
	
	@SuppressWarnings("unchecked")
	private <T, U> T[] loadup(U[] input, Class<T> t, Function<U, T> translate) {
		T[] result = (T[]) Array.newInstance(t, input.length);
		for(int i = 0; i < input.length; i ++)
			result[i] = translate.apply(input[i]);
		return result;		
	}
}
