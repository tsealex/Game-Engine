package engine;

public interface InputListener {
	public void setPressedKey(int key);
	public void setReleasedKey(int key);
	public void setCursorPosition(double x, double y);
	public void setPressedMouse(int button);
	public void setReleasedMouse(int button);
}
