package graphics;

import static org.lwjgl.opengl.GL20.*;

import java.nio.FloatBuffer;
import java.util.HashMap;
import java.util.Map;

import utils.BufferMaker;

class Shader {
	
	public static final String UNIFORM_TRANS = "data";
	
	private int programID;
	private int vShaderID, fShaderID;
	private Map<String, Integer> shadUniLoc = new HashMap<String, Integer>();
	
	public Shader(int programID, int vShaderID, int fShaderID)	{
		this.programID = programID;
		this.vShaderID = vShaderID;
		this.fShaderID = fShaderID;
	}

	protected void bindToUniform(FloatBuffer buffer, String attr)	{
		glUniform4(shadUniLoc.get(attr), buffer);
	}

	protected void begin()	{
		// Use this program and set up the uniform variables for binding
		 glUseProgram(programID);
		 shadUniLoc.put(UNIFORM_TRANS, 
				 glGetUniformLocation(programID, UNIFORM_TRANS));
	}
	
	protected void end()	{
		// Stop using this program
		glUseProgram(0);
	}
	
	protected void delete()	{
		// Delete this program so that it doesn't take up memory anymore
		glUseProgram(0);
		
		glDetachShader(programID, vShaderID);
		glDetachShader(programID, fShaderID);
		
		glDeleteShader(vShaderID);
		glDeleteShader(fShaderID);
		
		glDeleteProgram(programID);	
	}
}
