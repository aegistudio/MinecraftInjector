package net.aegistudio.mcinject.network;

import java.util.ArrayList;

import org.bukkit.map.MapView;

import net.aegistudio.reflect.method.AbstractExecutor;

@SuppressWarnings({"deprecation", "rawtypes"})
public interface PacketPoMapHandler {
	public static enum EnumHandler {
		IB$B(3, (c, v, r, co, ro, cl, rl) -> c.invoke(null, v.getId(), r, v.getScale().getValue())),
		IBLCB$IIII(4, (c, v, r, co, ro, cl, rl) -> c.invoke((int)v.getId(), v.getScale().getValue(), new ArrayList(), r, co, ro, cl, rl)),
		IBZLCB$IIII(4, (c, v, r, co, ro, cl, rl) -> c.invoke((int)v.getId(), v.getScale().getValue(), false, new ArrayList(), r, co, ro, cl, rl));
		
		public final int paramCount;
		public final PacketPoMapHandler handler;
		private EnumHandler(int paramCount, PacketPoMapHandler handler) {
			this.paramCount = paramCount;
			this.handler = handler;
		}
	}
	
	public Object playOutMap(AbstractExecutor constructor, MapView mapView, byte[] raster, int columnOffset,
			int rowOffset, int columnLength, int rowLength) throws Exception;
}