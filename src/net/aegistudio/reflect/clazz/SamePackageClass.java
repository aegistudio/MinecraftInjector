package net.aegistudio.reflect.clazz;

public class SamePackageClass extends AbsolutePackageClass{
	public <T> SamePackageClass(Class<T> sourceClass, String clazzName) throws ClassNotFoundException {
		super(sourceClass.getPackage().getName(), clazzName);
	}
	
	public SamePackageClass(AbstractClass sourceClass, String clazzName) throws ClassNotFoundException {
		this(sourceClass.getClazz(), clazzName);
	}
}
