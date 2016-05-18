package net.aegistudio.reflect.method;

import java.util.TreeSet;
import java.util.function.Function;

public class NamedExecutor extends AbstractExecutor {
	public NamedExecutor(Invocable[] invocableList, 
			Function<Invocable, Invocable> matching, String... names) throws Exception {
		
		super.invocable = invocable;
		TreeSet<String> aliases = new TreeSet<String>();
		if(names != null) for(String name : names) aliases.add(name);
		
		for(Invocable m : invocableList)
			if(aliases.size() == 0 || aliases.contains(m.getName()))  {
				super.invocable = matching.apply(m);
				if(super.invocable != null) break;
			}
		if(invocable == null) throw new NoSuchMethodException(aliases.toString());
	}

	public NamedExecutor(Invocable[] invocableList, String... names) throws Exception {
		this(invocableList, m -> m, names);
	}
}
