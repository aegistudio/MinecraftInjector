package net.aegistudio.reflect.method;

import java.util.function.Predicate;

public class ConditionedExecutor extends AbstractExecutor {
	public ConditionedExecutor(Invocable[] invocableList, Predicate<Invocable> matcher) 
			throws NoSuchMethodException, SecurityException {
		
		super.invocable = null;
		for(Invocable m : invocableList) {
			if(matcher.test(m)) {
				super.invocable = m;
				break;
			}
		}
		if(invocable == null) throw new NoSuchMethodException();
	}
}
