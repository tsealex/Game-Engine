package graphics;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import java.util.Collections;
import java.util.Comparator;

import engine.GameEngine;
import engine.InputListener;
import static engine.Config.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class GraphicsEngine implements GFXService {
	
	private long windowID;
	
	private Renderer gfxRdr;
	private GFXMaker gfxMkr;
	private GFXCollector gfxCol;
	
	private boolean running = false;
	private int drawnObj;
	
	private GLFWKeyCallback glKeys;
	private GLFWMouseButtonCallback glMouse;
	private GLFWCursorPosCallback glCursor;
	private InputListener inLsnr;

	public GraphicsEngine(GameEngine engine, InputListener inListener)	{
		inLsnr = inListener;
	}
	
	public void init()	{
		// Initialize GLFW in order to use its functions
		glfwInit();
		
		// Configure the window
		glfwWindowHint(GLFW_RESIZABLE, GL_FALSE);
		
		// Create the window using LWJGL
		windowID = glfwCreateWindow(ScrnWidth(), ScrnHeight(), 
				GAME_TITLE, NULL, NULL);
		
		if(windowID == NULL)	
			return;
		
		// Position the window
		ByteBuffer vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		
		glfwSetWindowPos(windowID, (GLFWvidmode.width(vidmode) - ScrnWidth())
				/ 2, (GLFWvidmode.height(vidmode) - ScrnHeight()) / 2);
		
		// Make the window context current 
		glfwMakeContextCurrent(windowID);
		GLContext.createFromCurrent();
		
		// Initialize
		gfxRdr = new Renderer();
		gfxMkr = new GFXMaker();
		gfxCol = new GFXCollector();
		
		glClearColor(1f, 1f, 1f, 1f);
		// Tell Renderer object to start
		gfxRdr.begin();
		
		running = true;
	}

	public void renderBegin() {
		// Loop: Clear -> Render -> Draw -> Report events
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
		drawnObj = 0;
	}
	
	public void renderEnd()	{
		glfwSwapBuffers(windowID); 
		glfwPollEvents();
	}
	
	public void end()	{
		// Tell Renderer to stop
		gfxRdr.end();
		glKeys.release();
	}
	
	public void handleInput()	{
		// Listen to the key events occur on the screen (window)
		glfwSetKeyCallback(windowID, glKeys = new GLFWKeyCallback() {
		    @Override
		    public void invoke(long window, int key, int scancode, int action, int mods) {
		    	if(action == GLFW_PRESS)
		    		inLsnr.setPressedKey(key);
		    	else if(action == GLFW_RELEASE)
		    		inLsnr.setReleasedKey(key);
		    }
		});
		
		// Listen to the mouse events
		glfwSetMouseButtonCallback(windowID, glMouse = new GLFWMouseButtonCallback() {
		    @Override
		    public void invoke(long window, int button, int action, int mods) {
		    	if(action == GLFW_PRESS)
		    		inLsnr.setPressedMouse(button);
		    	else
		    		inLsnr.setReleasedMouse(button);
		    }
		});
		
		glfwSetCursorPosCallback(windowID, glCursor = new GLFWCursorPosCallback() {
		    @Override
		    public void invoke(long window, double xpos, double ypos) {
		    	inLsnr.setCursorPosition(xpos, ypos);
		    }
		});
	}
	
	public void register(GFXComponent obj)	{
		// Set up some variables that help the registration  
		GFXDataCell gfxCell = obj.getGFXCell();
		String className = obj.getClass().getSimpleName();
		String path = gfxCell.Path();
		// If the texture for this path has been loaded to the program previously,
		// return its texture ID
		Texture texture = gfxCol.getTexture(path);
		if(texture != null)	{
			// If the object exists then it has been loaded into the program
			// We then need to set the texID of the object profile to the texture ID
			gfxCell.TexID(texture.ID());
		} else	{
			if(gfxCell.Animated())
				gfxCell.TexID(gfxCol.addTexture(gfxMkr
						.createAnimation(gfxCell.RegNum(), (int) gfxCell.Width(), 
								(int) gfxCell.Height(), path), path));
			else
				gfxCell.TexID(gfxCol.addTexture(gfxMkr
						.createTexture(path), path));
		}
		
		// If the VAO for this class has been loaded to the program previously, 
		// return its vertex array object ID
		VertexArray vao = gfxCol.getVAO(className);
		if(vao != null)	{
			// Similar to loading the texture
			gfxCell.VaoID(vao.ID());
		} else	{
			gfxCell.VaoID(gfxCol.addVAO(gfxMkr.createVAO(
					gfxCell.Height(), gfxCell.Width()), className));
		}
	}
	
	public void reinitialize()	{
		// TODO: Write the definition of that method
		// First step: Release the memory taken up by the Texture in OpenGL

		// Second step: Clear the lists that store the objects' information
	}

	@Override
	public void deregister(GFXComponent obj) {
		gfxCol.updateTextureUsage(obj.getGFXCell().TexID());
		gfxCol.updateVAOUsage(obj.getGFXCell().VaoID());
	}
	
	public int[] getEngineData()	{
		return new int[]	{
			drawnObj, gfxCol.getVAONumber(), gfxCol.getTextureNumber()
		};
	}
	
	public boolean Running()	{
		return running;
	}
	
	public long WindowID() {
		return windowID;
	}

	@Override
	public void requestForRender(GFXDataCell cell) {
		drawnObj++;
		gfxRdr.prepare(cell, gfxCol.getTexture(cell.TexID()));
		gfxRdr.render(gfxCol.getVAO(cell.VaoID()));
	}

	@Override
	public void register(GFXDataCell gfxCell, String identiy) {
		String path = gfxCell.Path();

		Texture texture = gfxCol.getTexture(path);
		if(texture != null)	{
			gfxCell.TexID(texture.ID());
		} else {
			if(gfxCell.Animated())
				gfxCell.TexID(gfxCol.addTexture(gfxMkr
					.createAnimation(gfxCell.RegNum(), (int) gfxCell.Width(), 
							(int) gfxCell.Height(), path), path));
			else
				gfxCell.TexID(gfxCol.addTexture(gfxMkr
					.createTexture(path), path));
		}

		gfxCell.VaoID(gfxCol.addVAO(gfxMkr.createVAO(
			gfxCell.Height(), gfxCell.Width()), identiy));
	}
}
