package net.aegistudio.mcinject;

public class ChatComponentManager {
	public ProxiedClass<ChatComponentText.Class> text;
	public ProxiedClass<IChatBaseComponent.Class> base;
	
	public ChatComponentManager(MinecraftServer server) {
		this.text = new ProxiedClass<ChatComponentText.Class>(server, s -> new ChatComponentText.Class(s));
		this.base = new ProxiedClass<IChatBaseComponent.Class>(server, s -> new IChatBaseComponent.Class(s));
	}
}
