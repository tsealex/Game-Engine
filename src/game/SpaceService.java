package game;

import game.SpaceManager.Position;

public interface SpaceService {
	public Position registerPosition(float x, float y);
	public void removePosition(Position pos);
}
