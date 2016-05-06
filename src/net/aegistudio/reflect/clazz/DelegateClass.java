package net.aegistudio.reflect.clazz;

public class DelegateClass extends AbstractClass {
	private final Class delegated;
	public DelegateClass(Class delegated) {
		this.delegated = delegated;
	}
	
	@Override
	public java.lang.Class<?> getClazz() {
		return delegated.getClazz();
	}

	@Override
	public String getPackage() {
		return delegated.getPackage();
	}
}
