package net.aegistudio.mcinject.tileentity;

import org.bukkit.command.CommandSender;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.Instance;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.NamedExecutor;

public class CommandBlockListenerAbstract extends Instance<CommandBlockListenerAbstract.Class> {
	public static class Class extends SamePackageClass {
		AbstractExecutor sender;
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "CommandBlockListenerAbstract");
			this.sender = new NamedExecutor(field(), "sender");
		}
	}
	
	public CommandBlockListenerAbstract(Class clazz, Object instance) {
		super(clazz, instance);
	}
	
	public CommandSender getSender() {
		return (CommandSender) clazz.sender.invoke(thiz);
	}
	
	public void setSender(CommandSender sender) {
		clazz.sender.invoke(thiz, sender);
	}
}
