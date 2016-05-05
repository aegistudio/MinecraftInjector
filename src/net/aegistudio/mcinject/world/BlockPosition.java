package net.aegistudio.mcinject.world;

public class BlockPosition {
	public Object blockPosition;
	public WorldManager access;
	public BlockPosition(WorldManager access, Object blockPosition) {
		this.access = access;
		this.blockPosition = blockPosition;
	}
	
	public Object getBlockPosition() {
		return this.blockPosition;
	}
}
