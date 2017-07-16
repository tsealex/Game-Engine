package graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import maths.Matrix;

class Renderer {
	
	private Shader defaultShader;
	// Determine whether or not to draw transparent image
	// when disabled, the performance will improve
	private boolean alphaEnabled = false;
	private int texID = -2;
	private int vaoID = -2;
	
	protected Renderer()	{
		defaultShader = new ShaderLoader()
			.loadShader("TextureShader.vs", "TextureShader.fs");
	}
	
	protected void prepare(GFXDataCell objGFX, Texture TEX)	{
		if(objGFX.Animated())
			TEX.CurrIndex(objGFX.ImgIndex());
		
		if(objGFX.Alpha())	{
			if(!alphaEnabled)	{
				glEnable(GL_BLEND);
				glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			}
		} else	{
			if(alphaEnabled)
				glDisable(GL_BLEND);
		}

		defaultShader.bindToUniform(objGFX.Attribute(), Shader.UNIFORM_TRANS);
		
		if(texID != TEX.ID())	{
			TEX.bind();
			texID = TEX.ID();
		}
	}
	
	protected void begin()	{
		// We use one single shader throughout the game
		defaultShader.begin();
	}

	protected void render(VertexArray VAO)	{
		if(vaoID != VAO.ID())
			VAO.bind();
		VAO.draw();
	}
	
	protected void end()	{
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
		glBindTexture(GL_TEXTURE_2D, 0);
		defaultShader.end();
	}
}
