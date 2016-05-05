package net.aegistudio.mcinject;

import org.bukkit.Server;

import net.aegistudio.mcinject.network.PacketManager;
import net.aegistudio.mcinject.network.PlayerHandle;
import net.aegistudio.mcinject.world.CraftWorldHandle;
import net.aegistudio.mcinject.world.WorldHandle;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.clazz.ThisClass;
import net.aegistudio.reflect.method.NamedExecutor;

/**
 * Inject through retrieving specified field, so that
 * I will obtain the craftbukkit and minecraft package.
 * 
 * @author aegistudio
 */

public class CraftMinecraftServer implements MinecraftServer {
	public final Server bukkitServer;
	public final AbstractClass craftbukkitClazz;
	
	public final AbstractClass minecraftServerClazz;
	public final Object minecraftServer;
	
	public CraftMinecraftServer(Server craftserver) throws Exception {
		this.bukkitServer = craftserver;
		this.craftbukkitClazz = new SamePackageClass(craftserver.getClass(), "CraftServer");
		
		NamedExecutor consoleField = new NamedExecutor(this.craftbukkitClazz.field(), "console");
		this.minecraftServer = consoleField.invoke(craftserver);
		
		this.minecraftServerClazz = new ThisClass(this.minecraftServer);
		this.worldManager = new CraftWorldHandle(this);
		this.packetManager = new PacketManager(this);
		this.playerManager = new PlayerHandle(this);
	}

	@Override
	public Object getMinecraftServer() {
		return this.minecraftServer;
	}
	
	public AbstractClass getMinecraftServerClass() {
		return this.minecraftServerClazz;
	}

	@Override
	public Server getBukkitServer() {
		return this.bukkitServer;
	}

	@Override
	public AbstractClass getBukkitServerClass() {
		return this.craftbukkitClazz;
	}

	private final WorldHandle worldManager;
	@Override
	public WorldHandle getWorldManager() {
		return worldManager;
	}
	
	private final PacketManager packetManager;
	@Override
	public PacketManager getPacketManager() {
		return packetManager;
	}

	private final PlayerHandle playerManager;
	@Override
	public PlayerHandle getPlayerManager() {
		return playerManager;
	}
}
