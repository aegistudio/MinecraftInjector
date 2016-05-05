package net.aegistudio.mcinject;


public class ProxiedClass <T extends net.aegistudio.reflect.clazz.Class> {
	T instance;
	final MakeInterface getter;
	public final MinecraftServer server;
	
	public ProxiedClass(MinecraftServer server, MakeInterface getter) {
		this.server = server;
		this.getter = getter;
	}
	
	@SuppressWarnings("unchecked")
	public T getClazz() {
		if(instance == null) try {
			instance = (T) getter.make(server);
		}
		catch(Exception e) {
			throw new RuntimeException("Inject failure!");
		}
		return instance;
	}
	
	public static interface MakeInterface {
		public net.aegistudio.reflect.clazz.Class make(MinecraftServer server) throws Exception;
	}
}
