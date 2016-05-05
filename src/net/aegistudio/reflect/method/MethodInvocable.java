package net.aegistudio.reflect.method;

public class MethodInvocable implements Invocable {
	private final java.lang.reflect.Method method;
	public MethodInvocable(java.lang.reflect.Method constructor) {
		this.method = constructor;
	}
	
	@Override
	public Object invoke(Object source, Object... paramList) throws Exception {
		return method.invoke(source, paramList);
	}

	@Override
	public String getName() {
		return method.getName();
	}

	@Override
	public Class<?>[] getParameterList() {
		return this.method.getParameterTypes();
	}

	@Override
	public void setAccessible(boolean accessible) {
		this.method.setAccessible(accessible);
	}

	@Override
	public Class<?> getReturnType() {
		return method.getReturnType();
	}
}
