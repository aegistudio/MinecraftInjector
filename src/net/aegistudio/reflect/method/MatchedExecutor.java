package net.aegistudio.reflect.method;

import java.util.Arrays;

public class MatchedExecutor extends NamedExecutor {
	public MatchedExecutor(Invocable[] invocableList, String name, Class<?>... param) 
			throws Exception {
		this(invocableList, new String[]{name}, param);
	}
	
	public MatchedExecutor(Invocable[] invocableList, String[] names, Class<?>... param) 
			throws Exception {
		super(invocableList, m -> Arrays.deepEquals(m.getParameterList(), param)? m : null, names);
	}
	
	public MatchedExecutor(Invocable[] invocableList, Class<?>... param) 
			throws Exception {
		this(invocableList, new String[]{}, param);
	}
}
