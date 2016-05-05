package net.aegistudio.reflect.clazz;

public class ThisClass extends AbstractClass {
	private final Class<?> clazz;
	public ThisClass(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public ThisClass(Object instance) {
		this(instance.getClass());
	}
	
	@Override
	public Class<?> getClazz() {
		return clazz;
	}

	@Override
	public String getPackage() {
		return clazz.getPackage().toString();
	}

}
