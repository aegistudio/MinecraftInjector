package net.aegistudio.mcinject.network;

import org.bukkit.inventory.ItemStack;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.AbstractClass;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.LengthedExecutor;
import net.aegistudio.reflect.method.NamedExecutor;

public class PacketPlayOutSetSlot extends Packet<PacketPlayOutSetSlot.Class> {
	public PacketPlayOutSetSlot(MinecraftServer server, Object instance) {
		super(server.getPacketManager().playOutSetSlot.getClazz(), instance, true);
	}
	
	public PacketPlayOutSetSlot(MinecraftServer server, int window, int slot, ItemStack itemStack) {
		this(server, server.getPacketManager().playOutSetSlot.getClazz()
				.newInstance(window, slot, itemStack));
	}
	
	public static class Class extends SamePackageClass implements Packet.Class{
		AbstractExecutor constructor;
		AbstractExecutor getItemStackHandle;
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "PacketPlayOutSetSlot");
			this.constructor = new LengthedExecutor(super.constructor(), 3);
			
			AbstractClass samePackageClazz = new SamePackageClass(server.getBukkitServerClass(), 
					"inventory.CraftItemStack");
			this.getItemStackHandle = new NamedExecutor(samePackageClazz.method(), "getHandle");
		}
		
		public Object getItemStack(ItemStack itemStack) {
			return this.getItemStackHandle.invoke(itemStack);
		}
		
		public Object newInstance(int window, int slot, ItemStack itemStack) {
			return this.constructor.invoke(null, window, slot, this.getItemStack(itemStack));
		}
	}
}
