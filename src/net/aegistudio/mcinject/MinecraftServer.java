package net.aegistudio.mcinject;

import org.bukkit.Server;

import net.aegistudio.mcinject.network.PacketManager;
import net.aegistudio.mcinject.network.PlayerManager;
import net.aegistudio.mcinject.world.WorldHandle;
import net.aegistudio.reflect.clazz.AbstractClass;

public interface MinecraftServer {
	public Object getMinecraftServer();
	public AbstractClass getMinecraftServerClass();
	
	public Server getBukkitServer();
	public AbstractClass getBukkitServerClass();
	
	public WorldHandle getWorldManager();
	public PacketManager getPacketManager();
	public PlayerManager getPlayerManager();
}
