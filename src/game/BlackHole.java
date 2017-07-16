package game;

import game.SpaceManager.Position;
import maths.Vector2D;

public class BlackHole extends Vector2D implements Position {

	protected BlackHole() {

	}

	@Override
	public long ID() {
		return -1;
	}

	@Override
	public boolean Updatable() {
		return false;
	}

	@Override
	public boolean Drawable() {
		return false;
	}

	@Override
	public void X(float x) {

	}

	@Override
	public void Y(float y) {

	}

	@Override
	public void changeX(float x) {

	}

	@Override
	public void changeY(float y) {

	}
}
