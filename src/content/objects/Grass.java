package content.objects;

import game.GameObject;

public class Grass extends GameObject{

	public Grass(float startX, float startY) {
		super(startX, startY, 100, 100, 0, 1, false, false, 1, "BrickedGrassPatch.png");
		deregisterPHXEngine();
	}

	@Override
	public void action(int actionCode) {
	}

	@Override
	public int getActionCode() {
		return 0;
	}

	@Override
	public String getPHXCategory() {
		return null;
	}

	@Override
	protected void Init() {
	}

	@Override
	protected void Update() {
	}

	@Override
	protected void Draw() {
	}
	

}
