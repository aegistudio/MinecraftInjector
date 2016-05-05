package net.aegistudio.reflect.method;

public class NamedExecutor extends AbstractExecutor {
	public NamedExecutor(Invocable[] invocableList, String name) throws Exception {
		super.invocable = invocable;
		for(Invocable m : invocableList)
			if(m.getName().equals(name))  {
				super.invocable = m; break;
			}
		if(invocable == null) throw new NoSuchMethodException(name);
	}
}
