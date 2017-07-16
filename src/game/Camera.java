package game;

import engine.GameEngine;
import maths.Vector2D;

public class Camera {
	// Position in world space
	private static Vector2D position = new Vector2D(0, 0);
	private static boolean shifted = true;

	public Camera(GameEngine engine) {

	}

	public static float X() {
		return position.X();
	}

	public static float Y() {
		return position.Y();
	}

	public static void shift(float x, float y) {
		shifted = true;
		position.changeX(x);
		position.changeY(y);
	}
	
	public static boolean Shifted()	{
		return shifted;
	}
	
	public void update()	{
		shifted = false;
	}
}
