package net.aegistudio.reflect.method;

public interface Invocable {
	public Object invoke(Object source, Object... paramList) throws Exception;
	
	public String getName();
	
	public Class<?>[] getParameterList();
	
	public void setAccessible(boolean accessible);
}
