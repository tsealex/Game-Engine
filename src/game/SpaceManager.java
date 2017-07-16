package game;

import java.util.HashMap;
import java.util.Map;

import engine.Config;
import engine.GameEngine;
import maths.Vector2D;

public class SpaceManager implements SpaceService {
	// Be aware that once an object is removed, the object should not continue
	// to call any method
	// because it would cause exception
	private long nextID = 0;

	public static final BlackHole BLACK_HOLE = new BlackHole();

	private class SpacePosition extends Vector2D implements Position {

		private long ID = -1;
		private boolean updatable = true;
		private boolean drawable = true;

		private SpacePosition(float x, float y, long ID) {
			super(x, y);
			this.ID = ID;
		}

		public long ID() {
			return ID;
		}

		public boolean Updatable() {
			return updatable;
		}

		public boolean Drawable() {
			return drawable;
		}
	}

	public interface Position {
		public long ID();

		public boolean Updatable();

		public boolean Drawable();

		public float X();

		public float Y();

		public void X(float x);

		public void Y(float y);

		public void changeX(float x);

		public void changeY(float y);
	}

	private Map<Long, SpacePosition> posMap;

	public SpaceManager(GameEngine engine) {
		posMap = new HashMap<Long, SpacePosition>();
	}

	public Position registerPosition(float x, float y) {
		SpacePosition pos = new SpacePosition(x, y, nextID);
		posMap.put(nextID, pos);
		nextID++;
		return pos;
	}

	public void removePosition(Position pos) {
		posMap.remove(pos.ID());
	}

	public void findUpdatableObjects() {
		// Three times
		float widthFactor = Config.ScrnWidth() * 1.5f * Config.REFRESH_RATE;
		float heightFactor = Config.ScrnHeight() * 1.5f * Config.REFRESH_RATE;
		for (SpacePosition pos : posMap.values()) {
			if (pos.updatable) {
				if (Math.abs(pos.X() - Camera.X()) > widthFactor
						|| Math.abs(pos.Y() - Camera.Y()) > heightFactor)
					pos.updatable = false;
			} else {
				if (Math.abs(pos.X() - Camera.X()) < widthFactor
						&& Math.abs(pos.Y() - Camera.Y()) < heightFactor)
					pos.updatable = true;
			}
		}
	}

	public void findDrawableObjects() {
		// If the object is outside the area two time bigger than the screen
		// space
		// then it becomes not drawable
		float widthFactor = Config.ScrnWidth() * 1.5f * Config.REFRESH_RATE;
		float heightFactor = Config.ScrnHeight() * 1.5f * Config.REFRESH_RATE;
		for (SpacePosition pos : posMap.values()) {
			if (pos.drawable) {
				if (Math.abs(pos.X() - Camera.X()) > widthFactor
						|| Math.abs(pos.Y() - Camera.Y()) > heightFactor)
					pos.drawable = false;
			} else {
				if (Math.abs(pos.X() - Camera.X()) < widthFactor
						&& Math.abs(pos.Y() - Camera.Y()) < heightFactor)
					pos.drawable = true;
			}
		}
	}

	public void clear() {
		// Each time the game is loaded or reinitialized, this method will be
		// invoked if a Coordinate system already exists
		posMap.clear();
	}
}
