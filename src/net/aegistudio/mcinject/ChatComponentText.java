package net.aegistudio.mcinject;

import net.aegistudio.reflect.clazz.Instance;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.LengthedExecutor;

public class ChatComponentText extends Instance<ChatComponentText.Class> {
	public static class Class extends SamePackageClass {
		AbstractExecutor constructor;
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "ChatComponentText");
			constructor = new LengthedExecutor(constructor(), 1);
		}
		
		public Object newInstance(String string) {
			return constructor.invoke(null, string);
		}
	}
	
	public ChatComponentText(MinecraftServer server, String chat) {
		super(server.getChatComponentManager().text.getClazz(), 
				server.getChatComponentManager().text.getClazz().newInstance(chat));
	}
}
