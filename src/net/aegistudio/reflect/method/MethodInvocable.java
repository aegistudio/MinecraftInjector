package net.aegistudio.reflect.method;

public class MethodInvocable implements Invocable {
	private final java.lang.reflect.Method constructor;
	public MethodInvocable(java.lang.reflect.Method constructor) {
		this.constructor = constructor;
	}
	
	@Override
	public Object invoke(Object source, Object... paramList) throws Exception {
		return constructor.invoke(source, paramList);
	}

	@Override
	public String getName() {
		return constructor.getName();
	}

	@Override
	public Class<?>[] getParameterList() {
		return this.constructor.getParameterTypes();
	}

	@Override
	public void setAccessible(boolean accessible) {
		this.constructor.setAccessible(accessible);
	}
}
