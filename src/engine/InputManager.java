package engine;

import game.Camera;

import java.util.HashMap;
import java.util.Map;

import maths.Vector2D;

public class InputManager implements InputListener {
	
	// http://www.glfw.org/docs/latest/group__keys.html
	public enum	Key	{
		A(65), B(66), C(67), D(68), E(69), F(70), G(71), H(72), I(73), J(74), 
		K(75), L(76), M(77), N(78), O(79), P(80), Q(81), R(82), S(83), T(84), 
		U(85), V(86), W(87), X(88), Y(89), Z(90), SPACE(256), ENTER(257), ESC(256),
		SHIFT(32), ZERO(48), ONE(49), TWO(50), THREE(51), FOUR(52), FIVE(53), 
		SIX(54), SEVEN(55), EIGHT(56), NINE(57), TAB(258), DOWN(264), UP(265), 
		RIGHT(262), LEFT(263), DELETE(261), BACKSPACE(259);
		
		private int code;
		
		Key(int code)	{
			this.code = code;
		}
		
		public int Code()	{
			return this.code;
		}
	}
	
	public enum Mouse	{
		LEFT(0), MIDDLE(2), RIGHT(1);
		
		private int code;
		
		Mouse(int code)	{
			this.code = code;
		}
		
		public int Code()	{
			return this.code;
		}
	}
	
	private static Map<Integer, Boolean> keys = new HashMap<Integer, Boolean>();
	private static Map<Integer, Boolean> mouse = new HashMap<Integer, Boolean>();
	private static double mouseX, mouseY;
	
	protected InputManager(GameEngine engine)	{ 
		for(Key k : Key.values())
			keys.put(k.Code(), false);
		for(Mouse m : Mouse.values())
			mouse.put(m.Code(), false);
	}
	
	public void setPressedKey(int key)	{
		keys.put(key, true);
	}
	
	public void setReleasedKey(int key)	{
		keys.put(key, false);
	}
	
	public void setCursorPosition(double x, double y)	{
		mouseX = x;
		mouseY = y;
	}
	
	public static boolean contain(Key key)	{
		return keys.get(key.Code());
	}
	
	public static Vector2D getCursorPosition()	{
		return new Vector2D((float) mouseX - Config.ScrnWidth() / 2 + Camera.X(),
				(float) mouseY * - 1 + Config.ScrnHeight() / 2 + Camera.Y());
	}

	@Override
	public void setPressedMouse(int button) {
		mouse.put(button, true);
	}

	@Override
	public void setReleasedMouse(int button) {
		mouse.put(button, false);
	}
	
	public static boolean contain(Mouse button)	{
		return mouse.get(button.Code());
	}
}
