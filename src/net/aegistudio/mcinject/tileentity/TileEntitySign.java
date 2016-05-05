package net.aegistudio.mcinject.tileentity;

import java.lang.reflect.Array;
import org.bukkit.entity.Player;

import net.aegistudio.mcinject.ChatComponentText;
import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.mcinject.entity.EntityPlayer;
import net.aegistudio.mcinject.network.Packet;
import net.aegistudio.mcinject.network.PacketPlayOutUpdateSign;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.ConditionedExecutor;
import net.aegistudio.reflect.method.LengthedExecutor;
import net.aegistudio.reflect.method.NamedExecutor;

public class TileEntitySign extends TileEntity<TileEntitySign.Class> {
	public static class Class extends SamePackageClass implements TileEntity.Class {
		AbstractExecutor constructor;
		AbstractExecutor setOwner;
		AbstractExecutor lines;
		AbstractExecutor getUpdatePacket;
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "TileEntitySign");
			constructor = new LengthedExecutor(constructor(), 0);
			
			SamePackageClass entityHuman = new SamePackageClass(
					server.getMinecraftServerClass(), "EntityHuman");
			
			setOwner = new ConditionedExecutor(method(), m ->
				m.getReturnType() == void.class && m.getParameterList().length == 1
					&& m.getParameterList()[0] == entityHuman.getClazz());
			
			lines = new NamedExecutor(field(), "lines");
			getUpdatePacket = new NamedExecutor(method(), "getUpdatePacket");
		}
		
		public Object newInstance() {
			return constructor.invoke(null);
		}
		
		public void setOwner(Object tileEntitySign, Object owner) {
			setOwner.invoke(tileEntitySign, owner);
		}
		
		public void setLine(Object tileEntitySign, int index, Object chatComponentText) {
			Array.set(lines.invoke(tileEntitySign), index, chatComponentText);
		}
		
		public Object getLine(Object tileEntitySign, int index) {
			return Array.get(lines.invoke(tileEntitySign), index);
		}
		
		public Object getUpdatePacket(Object tileEntitySign) {
			return getUpdatePacket.invoke(tileEntitySign);
		}
	}
	
	private final MinecraftServer server;
	public TileEntitySign(MinecraftServer server) {
		super(server.getTileEntityManager().tileEntitySign.getClazz(), 
				server.getTileEntityManager().tileEntitySign.getClazz().newInstance());
		this.server = server;
	}
	
	public void setOwner(EntityPlayer player) {
		clazz.setOwner(thiz, player.thiz);
	}
	
	public void setOwner(Player player) {
		this.setOwner(server.getPlayerManager().getHandle(player));
	}
	
	public void setLine(int index, String line) {
		clazz.setLine(thiz, index, new ChatComponentText(server, line));
	}
	
	public Packet<PacketPlayOutUpdateSign.Class> getUpdatePacket() {
		Object updatePacket = clazz.getUpdatePacket(thiz);
		PacketPlayOutUpdateSign.Class updateClazz = server.getPacketManager().playOutUpdateSign.getClazz();
		return new Packet<PacketPlayOutUpdateSign.Class>(updateClazz, updatePacket, true);
	}
}
