package net.aegistudio.mcinject.network;

import java.util.ArrayList;

import org.bukkit.map.MapView;

import net.aegistudio.mcinject.MinecraftServer;
import net.aegistudio.reflect.clazz.SamePackageClass;
import net.aegistudio.reflect.method.AbstractExecutor;
import net.aegistudio.reflect.method.Invocable;
import net.aegistudio.reflect.method.ThisExecutor;

public class PacketPlayOutMap extends Packet<PacketPlayOutMap.Class> {
	public static class Class extends SamePackageClass implements Packet.Class {
		AbstractExecutor constructor;
		EnumHandler handler;
		
		public Class(MinecraftServer server) throws Exception {
			super(server.getMinecraftServerClass(), "PacketPlayOutMap");
			
			for(Invocable constructor : constructor())
				for(EnumHandler handler : EnumHandler.values())
					if(handler.paramCount == constructor.getParameterList().length) {
						this.constructor = new ThisExecutor(constructor);
						this.handler = handler;
						break;
					}
			if(this.constructor == null) throw new NoSuchMethodException();
		}
	}
	
	static interface Handler {
		public abstract Object playOutMap(AbstractExecutor constructor, MapView mapView, byte[] raster, int columnOffset,
				int rowOffset, int columnLength, int rowLength);
	}
	
	@SuppressWarnings({"deprecation", "rawtypes"})
	public static enum EnumHandler implements Handler{
		IB$B(3, (c, v, r, co, ro, cl, rl) -> c.invoke(null, v.getId(), r, v.getScale().getValue())),
		IBLCB$IIII(8, (c, v, r, co, ro, cl, rl) -> c.invoke(null, (int)v.getId(), v.getScale().getValue(), new ArrayList(), r, co, ro, cl, rl)),
		IBZLCB$IIII(9, (c, v, r, co, ro, cl, rl) -> c.invoke(null, (int)v.getId(), v.getScale().getValue(), false, new ArrayList(), r, co, ro, cl, rl));
		
		public final int paramCount;
		private final PacketPlayOutMap.Handler handler;
		private EnumHandler(int paramCount, PacketPlayOutMap.Handler handler) {
			this.paramCount = paramCount;
			this.handler = handler;
		}
		@Override
		public Object playOutMap(AbstractExecutor constructor, MapView mapView, byte[] raster, int columnOffset,
				int rowOffset, int columnLength, int rowLength) {
			return this.handler.playOutMap(constructor, mapView, raster, columnOffset, rowOffset, columnLength, rowLength);
		}
	}
	
	PacketPlayOutMap(MinecraftServer server, Object instance) {
		super(server.getPacketManager().playOutMap.getClazz(), instance, true);
	}
	
	public PacketPlayOutMap(MinecraftServer server, MapView mapView, byte[] raster, int columnOffset,
			int rowOffset, int columnLength, int rowLength) {
		this(server, server.getPacketManager().playOutMap.getClazz().handler.playOutMap(
				server.getPacketManager().playOutMap.getClazz().constructor, 
				mapView, raster, columnOffset, rowOffset, columnLength, rowLength));
	}
}
