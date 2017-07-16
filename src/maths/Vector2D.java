package maths;

public class Vector2D {
	
	private float x, y;

	public Vector2D()	{ }
	
	public Vector2D(float x, float y)	{
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "Vector2D [x=" + x + ", y=" + y + "]";
	}

	public void X(float x) {
		this.x = x;
	}

	public void Y(float y) {
		this.y = y;
	}
	
	public void changeX(float x) {
		this.x += x;
	}

	public void changeY(float y) {
		this.y += y;
	}

	public float X() {
		return x;
	}

	public float Y() {
		return y;
	}
	

}
