package net.aegistudio.reflect.clazz;

public class ThisClass extends AbstractClass {
	private final java.lang.Class<?> clazz;
	public ThisClass(java.lang.Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public ThisClass(Object instance) {
		this(instance.getClass());
	}
	
	@Override
	public java.lang.Class<?> getClazz() {
		return clazz;
	}

	@Override
	public String getPackage() {
		return clazz.getPackage().toString();
	}
}
