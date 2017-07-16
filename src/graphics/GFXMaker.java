package graphics;

import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL12.*;

import utils.BufferMaker;
import utils.ImageLoader;
import static engine.Config.*;
import static org.lwjgl.opengl.GL11.*;

class GFXMaker {

	private ImageLoader imageLoader = new ImageLoader("graphics");

	// The whole texture image will be used to cover all the vertex
	protected final float[] DFLT_TEX_BUFFER = { 0, 1, 0, 0, 1, 0, 1, 1 };
	// This engine only support drawing rectangle shapes
	protected final byte[] DFLT_IDX_BUFFER = { 0, 1, 2, 2, 0, 3 };

	protected GFXMaker() {
	}

	protected Texture createTexture(String path) {
		// Create and initialize the texture buffer object in OpenGL
		int texID = glGenTextures();
		glBindTexture(GL_TEXTURE_2D, texID);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

		// Create a BufferedImage and turn it into an integer buffer, then pass
		// it to OpenGL
		BufferedImage temp = imageLoader.getBufferedImage(path);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, temp.getWidth(),
				temp.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE,
				BufferMaker.convertImage(temp));
		glBindTexture(GL_TEXTURE_2D, 0);

		return new Texture(texID);
	}

	protected VertexArray createVAO(float height, float width) {
		VertexArray VAO = new VertexArray();

		// Using object's data to construct a vertex array
		// Origin is set to (0,0) so that we can easily transform
		// the object
		float w = width / ScrnWidth();
		float h = height / ScrnHeight();
		float[] vtx = new float[] { -w, h, -w, -h, w, -h, w, h };

		VAO.initVBOs(vtx, DFLT_TEX_BUFFER, DFLT_IDX_BUFFER);

		return VAO;
	}

	protected Animation createAnimation(int number, int width, int height,
			String path) {
		BufferedImage spriteSheet = imageLoader.getBufferedImage(path);
		BufferedImage[] temp = new BufferedImage[number];
		int[] texIDs = new int[number];

		// Cut up the image
		int col = spriteSheet.getWidth() / width;
		int row = spriteSheet.getHeight() / height;

		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				if (number > r * col + c)
					temp[r * col + c] = spriteSheet.getSubimage(c * width, r
							* height, width, height);
			}
		}

		// OpenGL part: same as the one in for creating a Texture object
		for (int i = 0; i < texIDs.length; i++) {
			texIDs[i] = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, texIDs[i]);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, temp[i].getWidth(),
					temp[i].getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE,
					BufferMaker.convertImage(temp[i]));
			glBindTexture(GL_TEXTURE_2D, 0);
		}

		return new Animation(texIDs);
	}
}
