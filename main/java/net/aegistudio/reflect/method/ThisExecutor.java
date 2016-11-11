package net.aegistudio.reflect.method;

public class ThisExecutor extends AbstractExecutor {
	public ThisExecutor(Invocable invocable) {
		super.invocable = invocable;
	}
}
