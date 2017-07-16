package graphics;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;

public class Animation extends Texture {

	private int[] texIDArray;
	private int currIndex;

	protected Animation(int[] texIDs) {
		texIDArray = texIDs;
		super.incrUsage();
	}

	protected void CurrIndex(int index) {
		if (index < texIDArray.length && index >= 0)
			currIndex = index;
	}

	// //////////////////////////////////////
	// OpenGL Setup                        //
	// //////////////////////////////////////

	@Override
	protected void bind() {
		glBindTexture(GL_TEXTURE_2D, texIDArray[currIndex]);
	}

	@Override
	protected void delete() {
		// TODO!
	}

	@Override
	protected int ID() {
		return texIDArray[currIndex];
	}
}
