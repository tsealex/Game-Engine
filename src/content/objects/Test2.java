package content.objects;

import java.util.ArrayList;

import game.GameObject;

public class Test2 extends GameObject {

	public Test2(float x, float y , String path) {
		super(x, y, 128, 85, 0, 0.5f, false, false,
				1, path);
		init();
	}

	@Override
	protected void Init() {
		

	}

	@Override
	protected void Update() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void Draw() {
		// TODO Auto-generated method stub

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

}
