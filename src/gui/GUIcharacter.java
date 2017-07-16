package gui;

import game.GameObject;

public class GUIcharacter extends GameObject{
	int characterNumber;
	public GUIcharacter(float xPlacement, float yPlacement, int characterNumber) {
		super(xPlacement, yPlacement, 10, 15, 0, 1.0f, false,  false, 27, "SciFiFont.png");
		this.characterNumber = characterNumber;
		
		deregisterPHXEngine();
	}

	@Override
	protected void Init() {	
	}

	@Override
	protected void Update() {
	}

	@Override
	protected void Draw() {	
		this.setAnimation(characterNumber, characterNumber, 1);
	}

	@Override
	public void action(int actionCode) {
	}

	@Override
	public int getActionCode() {
		return -1;
	}

	@Override
	public String getPHXCategory() {
		return null;
	}
}
