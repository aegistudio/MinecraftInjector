package net.aegistudio.reflect.clazz;

public class AbsolutePackageClass extends AbstractClass {
	private final java.lang.Class<?> clazz;
	private final String packageName;
	private final String absolutePath;
	public AbsolutePackageClass(String packageName, String clazzName) 
			throws ClassNotFoundException {
		this.packageName = packageName;
		this.absolutePath = packageName + "." + clazzName;
		clazz = java.lang.Class.forName(this.absolutePath);
	}
	
	@Override
	public java.lang.Class<?> getClazz() {
		return clazz;
	}
	
	public String getAbsolutePath() {
		return this.absolutePath;
	}

	@Override
	public String getPackage() {
		return packageName;
	}
}
