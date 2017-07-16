package graphics;

import java.nio.FloatBuffer;

import utils.BufferMaker;
import engine.Config;
import engine.GameClock;
import game.Camera;

/**
 * {@link GFXDataCell} contains the graphic information of a single GameObject
 * or the objects that implement {@link GFXComponent} interface.
 * 
 * @author Alex
 */
public class GFXDataCell {

	private FloatBuffer attribute;
	// Through this variable to obtain the VertexArray object from
	// GraphicsManager
	private int vaoID = -1;
	// Through this variable to obtain the Texture object from GraphicsManager
	private int texID = -1;
	// Store the width and height
	private float width, height;
	// Used to determine which animation frame should be displayed
	private int imgIndex = 0;
	// How many region the sprite sheet will be cut up into
	private int regNum;
	// The profile returns Animation object or Texture object
	private boolean animated = false;
	// Texture image location
	private String path;
	// Requires alpha channel
	private boolean alpha = true;
	// For animation
	private int animCount;

	public GFXDataCell(int width, int height, String path) {
		this.path = path;
		this.width = width;
		this.height = height;
	}

	public GFXDataCell(int width, int height, int regNum, String path) {
		this.path = path;
		this.width = width;
		this.height = height;
		this.regNum = regNum;
		this.animated = true;
	}

	public float Width() {
		return width;
	}

	public float Height() {
		return height;
	}

	public int ImgIndex()	{
		return imgIndex;
	}
	
	public int RegNum() {
		return regNum;
	}

	protected String Path() {
		return path;
	}

	protected boolean Animated() {
		return animated;
	}

	protected int TexID() {
		return texID;
	}

	protected int VaoID() {
		return vaoID;
	}

	protected void TexID(int ID) {
		texID = ID;
	}

	protected void VaoID(int ID) {
		vaoID = ID;
	}

	protected boolean Alpha() {
		return alpha;
	}

	public void setAttribute(float x, float y, float rotation, float scale) {
		attribute = BufferMaker.covertFloat(new float[] {
			(float) Math.cos(rotation) * scale,
			(float) Math.sin(rotation) * scale,
			(x - Camera.X()) / Config.ScrnWidth() * 2,
			(y - Camera.Y()) / Config.ScrnHeight() * 2 
		});
	}

	protected FloatBuffer Attribute() {
		return attribute;
	}
	
	public void setAnimation(int min, int max, int frame)	{
		if(GameClock.Time() - animCount >= frame)	{
			animCount = GameClock.Time();
			imgIndex++;
			if(imgIndex > max || imgIndex < min)
				imgIndex = min;
		}
	}
	
	public void setAnimation(int frame)	{
		if(GameClock.Time() - animCount >= frame)	{
			animCount = GameClock.Time();
			imgIndex++;
			if(imgIndex >= regNum)
				imgIndex = 0;
		}
	}
}
