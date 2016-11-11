package net.aegistudio.reflect.method;

public class LengthedExecutor extends NamedExecutor {
	public LengthedExecutor(Invocable[] invocableList, int length, String... names) 
			throws Exception {
		super(invocableList, m -> m.getParameterList().length == length? m : null, names);
	}

	public LengthedExecutor(Invocable[] invocableList, int length) throws Exception {
		this(invocableList, length, "");
	}
}
