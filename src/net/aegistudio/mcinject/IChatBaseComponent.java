package net.aegistudio.mcinject;

import net.aegistudio.reflect.clazz.Instance;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.NamedExecutor;

public class IChatBaseComponent extends Instance<IChatBaseComponent.Class> {
	public static class Class extends SamePackageClass {
		AbstractExecutor constructor;
		AbstractExecutor getText;
		AbstractExecutor toPlainText;
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "IChatBaseComponent");
			this.getText = new NamedExecutor(method(), "getText", "e");
			this.toPlainText = new NamedExecutor(method(), "toPlainText", "c");
		}
		
		public Object newInstance(String string) {
			return constructor.invoke(null, string);
		}
	}
	
	public IChatBaseComponent(MinecraftServer server, Object instance) {
		super(server.getChatComponentManager().base.getClazz(), instance);
	}
	
	public String getText() {
		return (String)clazz.getText.invoke(this);
	}
	
	public String toPlainText() {
		return (String)clazz.toPlainText.invoke(this);
	}
}