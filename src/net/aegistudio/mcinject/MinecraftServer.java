package net.aegistudio.mcinject;

import org.bukkit.Server;

import net.aegistudio.mcinject.block.Block;
import net.aegistudio.mcinject.entity.EntityManager;
import net.aegistudio.mcinject.network.PacketManager;
import net.aegistudio.mcinject.network.PlayerHandle;
import net.aegistudio.mcinject.tileentity.TileEntityManager;
import net.aegistudio.mcinject.world.WorldHandle;
import net.aegistudio.reflect.clazz.AbstractClass;

public interface MinecraftServer {
	public Object getMinecraftServer();
	public AbstractClass getMinecraftServerClass();
	
	public Server getBukkitServer();
	public AbstractClass getBukkitServerClass();
	
	public WorldHandle getWorldManager();
	public PacketManager getPacketManager();
	public PlayerHandle getPlayerManager();
	public TileEntityManager getTileEntityManager();
	public EntityManager getEntityManager();
	
	public ChatComponentManager getChatComponentManager();
	
	public Block.Class getBlockClass();
}
