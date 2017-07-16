package content.objects;

import game.GameObject;

public class Planet extends GameObject {
	
	public Planet(float placeX, float placeY, int width, String path)
	{
		super(placeX, placeY, width, width, 0, 3, true, false, 1, path); 
		
	}

	@Override
	public void action(int actionCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getActionCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getPHXCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void Init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void Update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void Draw() {
		// TODO Auto-generated method stub
		
	}
	

}
