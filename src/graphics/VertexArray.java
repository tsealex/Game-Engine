package graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;

import static utils.BufferMaker.*;

public class VertexArray {
		
	private int vaoID;
	private int[] vboID;
	private int indexCount;
	private int usage;
	private boolean markedDeletion;
	
	protected VertexArray()	{
		// Only three vertex buffer objects are needed. They store the information
		// of vertices, textures and indices of an object respectively
		vboID = new int[3];
		usage++;
	}
	
	////////////////////////////////////////
	//            OpenGL Setup            //
	////////////////////////////////////////
	
	protected void initVBOs(float[] vtx, float[] tex, byte[] idx)	{
		// Create a vertex array to contain the VBO in OpenGL
		vaoID = glGenVertexArrays();
		// Start writing this VAO
		glBindVertexArray(vaoID);
		
		// Create and define the vertex buffer that stores the vertices data
		vboID[0] = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID[0]);
		
		// Load data into the VBO
		glBufferData(GL_ARRAY_BUFFER, covertFloat(vtx), GL_STATIC_DRAW);
		
		// Specify which input stream in the shader this buffer will feed, the 
		// size of each vertex and the type of the vertex data
		glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(0);
		
		// Do the same to the texture buffer
		vboID[1] = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboID[1]);
		glBufferData(GL_ARRAY_BUFFER, covertFloat(tex), GL_STATIC_DRAW);
		glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
		glEnableVertexAttribArray(1);
		
		// Do the same to the index buffer too
		vboID[2] = glGenBuffers();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID[2]);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, convertByte(idx), GL_STATIC_DRAW);
		
		// Used to keep track of how many indices are stored in the indices array
		indexCount = idx.length;
		
		// Finish writing both the VAO and the VBOs
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindVertexArray(0);
	}

	protected void delete()	{
		// Stop using and delete the VAO
		glBindVertexArray(0);
		glDeleteVertexArrays(vaoID);
		
		// Stop using and delete the VBOs
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
		for(int vbo : vboID)
			glDeleteBuffers(vbo);
	}
	
	protected void bind()	{
		// Use this VAO
		glBindVertexArray(vaoID);
		glEnableVertexAttribArray(0);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID[2]);
	}
	
	protected void draw()	{
		// Start to draw
		glDrawElements(GL_TRIANGLES, indexCount, GL_UNSIGNED_BYTE, 0);
	}
	
	protected void unbind()	{
		// Stop drawing this VAO
		glDisableVertexAttribArray(0);
		glBindVertexArray(0);
	}
	
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
	
	public int ID()	{
		return vaoID;
	}
}
