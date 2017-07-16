package content.objects;



import java.util.ArrayList;

import content.MainGame;
import maths.Vector2D;
import engine.Config;
import engine.GEService;
import engine.InputManager;
import engine.InputManager.Key;
import engine.InputManager.Mouse;
import game.Camera;
import game.EffectRequest;
import game.GameObject;

public class TestObj extends GameObject {

	Vector2D target = new Vector2D();
	float v = 5;
	float vx, vy;
	float offx, offy;
	boolean clicked = false;
	boolean leftClicked = false;
	boolean effectEnabled;
	
	public TestObj() {
		super(0, 0, 128, 128, 0, 0.5f, false, false,
				64, "test1024.png");
		// TODO Auto-generated constructor stub
		
	}

	@Override
	protected void Init() {

	}

	@Override
	protected void Update() {
		if(InputManager.contain(Mouse.RIGHT) && !clicked)	{
			target = InputManager.getCursorPosition();
			clicked = true;
			float dx = target.X() - X();
			float dy = target.Y() - Y();
			Effect().requestForEffect(new EffectRequest(0, 30, 3, target.X(), target.Y(), 0, 1), 1);

			double d = Math.sqrt(dx * dx + dy * dy);
			double m = v / d;
			vx = (float) (dx * m);
			vy = (float) (dy * m);
		}
		if(InputManager.contain(Mouse.LEFT))	{
			if(!leftClicked)	{
				Vector2D pos = InputManager.getCursorPosition(); 
				if(Math.abs(pos.X() - X()) < 40 && Math.abs(pos.Y() - Y()) < 40)	{
					vx = 0;
					vy = 0;
					offx = pos.X() - X();
					offy = pos.Y() - Y();
					X(pos.X() - offx);
					Y(pos.Y() - offy);
					leftClicked = true;
				}
			} else if(leftClicked)	{
				Vector2D pos = InputManager.getCursorPosition(); 
				X(pos.X() - offx);
				Y(pos.Y() - offy);
			}
		} 
		
		if(Math.abs(X() - target.X()) < 2 || Math.abs(Y() - target.Y()) < 2)	{
			vx = 0;
			vy = 0;
		} 
		
		
		changeX(vx);
		changeY(vy);
		if(clicked)	{
			if(!InputManager.contain(Mouse.RIGHT))
				clicked = false;
		}
		if(leftClicked)	{
			if(!InputManager.contain(Mouse.LEFT))
				leftClicked = false;
		}
		
		if(InputManager.contain(Key.A))
			Camera.shift(-1f, 0f);
		else if(InputManager.contain(Key.D))
			Camera.shift(1, 0);
		if(InputManager.contain(Key.W))
			Camera.shift(0, 1);
		else if(InputManager.contain(Key.S))
			Camera.shift(0, -1);
		
		if(InputManager.contain(Key.V))	{
			if(!effectEnabled)	{
				effectEnabled = true;
				Effect().requestForEffect(new EffectRequest(0, 24, 3, X(), Y(), 0, 1), 1);
			}
		} else	{
			effectEnabled = false;
		}
	}

	@Override
	protected void Draw() {
		setAnimation(0, 64, 2);
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
