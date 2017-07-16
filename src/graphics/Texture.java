package graphics;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
	
	private int texID;
	private int usage;
	private boolean markedDeletion = false;
	
	protected Texture()	{ 
		usage++;
	}
	
	protected Texture(int texID)	{
		usage++;
		this.texID = texID;
	}
	
	////////////////////////////////////////
	//            OpenGL Setup            //
	////////////////////////////////////////
		
	protected void bind()	{
		glBindTexture(GL_TEXTURE_2D, texID);
	}
	
	protected void unbind()	{
		glBindTexture(GL_TEXTURE_2D, 0);
	}
	
	protected int ID()	{
		return texID;
	}
	
	protected void delete()	{
		// TODO!
	}
	
	protected void CurrIndex(int index)	{ }
	
	////////////////////////////////////////
	//            Usage Tracker           //
	////////////////////////////////////////
	
	protected void incrUsage()	{
		usage++;
	}
	
	protected void decrUsage()	{
		usage--;
	}
	
	protected int Usage()	{
		return usage;
	}
	
	protected boolean MarkedDeletion()	{
		return markedDeletion;
	}
	
	protected void mark()	{
		markedDeletion = true;
	}
	
	protected void unmark()	{
		markedDeletion = false;
	}
}
