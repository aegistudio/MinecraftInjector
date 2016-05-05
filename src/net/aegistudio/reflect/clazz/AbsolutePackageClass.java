package net.aegistudio.reflect.clazz;

public class AbsolutePackageClass extends AbstractClass {
	private final Class<?> clazz;
	private final String packageName;
	private final String absolutePath;
	public AbsolutePackageClass(String packageName, String clazzName) 
			throws ClassNotFoundException {
		this.packageName = packageName;
		this.absolutePath = packageName + "." + clazzName;
		clazz = Class.forName(this.absolutePath);
	}
	
	@Override
	public Class<?> getClazz() {
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
