package net.aegistudio.mcinject;

public class ChatComponentManager {
	public ProxiedClass<ChatComponentText.Class> text;
	
	public ChatComponentManager(MinecraftServer server) {
		this.text = new ProxiedClass<ChatComponentText.Class>(server, s -> new ChatComponentText.Class(s));
	}
}
