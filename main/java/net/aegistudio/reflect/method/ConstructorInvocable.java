package net.aegistudio.reflect.method;

public class ConstructorInvocable implements Invocable {
	private final java.lang.reflect.Constructor<?> constructor;
	public ConstructorInvocable(java.lang.reflect.Constructor<?> constructor) {
		this.constructor = constructor;
	}
	
	@Override
	public Object invoke(Object source, Object... paramList) throws Exception {
		return constructor.newInstance(paramList);
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public Class<?>[] getParameterList() {
		return this.constructor.getParameterTypes();
	}

	@Override
	public void setAccessible(boolean accessible) {
		this.constructor.setAccessible(accessible);
	}

	@Override
	public Class<?> getReturnType() {
		return constructor.getDeclaringClass();
	}
}
