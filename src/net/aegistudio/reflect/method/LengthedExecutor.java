package net.aegistudio.reflect.method;

public class LengthedExecutor extends AbstractExecutor {
	public LengthedExecutor(Invocable[] invocableList, String name, int length) 
			throws NoSuchMethodException, SecurityException {
		
		super.invocable = null;
		for(Invocable m : invocableList)
			if(m.getName().equals(name)) 
				if(m.getParameterList().length == length) {
					invocable = m; break;
				}
		if(invocable == null) throw new NoSuchMethodException(name);
	}

	public LengthedExecutor(Invocable[] invocableList, int length)
			throws NoSuchMethodException, SecurityException {
		this(invocableList, "", length);
	}
}
