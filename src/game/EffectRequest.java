package game;

import engine.GameClock;

public class EffectRequest {
	protected float[] floatData = new float[4];
	protected int[] intData = new int[3];

	public EffectRequest(int id, int duration, int frame, float x, float y,
			float rotation, float scale) {
		// x and y are in the game space
		floatData[0] = x;
		floatData[1] = y;
		floatData[2] = rotation;
		floatData[3] = scale;
		intData[0] = id;
		intData[1] = duration + GameClock.Time();
		intData[2] = frame;
	}
}
