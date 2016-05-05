package net.aegistudio.reflect.method;

public class AbstractExecutor {
	public Invocable invocable;
	
	public Object invoke(Object thiz, Object... args) {
		try {
			invocable.setAccessible(true);
			Object result = invocable.invoke(thiz, args);
			return result;
		}
		catch(Exception e) {
			return null;
		}
		finally {
			invocable.setAccessible(false);
		}
	}
}
