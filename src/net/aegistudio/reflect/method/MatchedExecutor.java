package net.aegistudio.reflect.method;

import java.util.Arrays;

public class MatchedExecutor extends AbstractExecutor {
	public MatchedExecutor(Invocable[] invocableList, String name, Class<?>... param) 
			throws NoSuchMethodException, SecurityException {
		
		super.invocable = null;
		for(Invocable m : invocableList) {
			if(name != null && !name.equals(invocable.getName()))
				continue;
			
			if(Arrays.deepEquals(m.getParameterList(), param)) {
				invocable = m; break;
			}
		}
		if(invocable == null) throw new NoSuchMethodException();
	}

	public MatchedExecutor(Invocable[] invocableList, Class<?>... param) 
			throws NoSuchMethodException, SecurityException {
		this(invocableList, null, param);
	}
}
