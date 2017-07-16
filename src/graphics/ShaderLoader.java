package graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import utils.TextLoader;

/**
 * ShaderLoader is in graphics package because it involves in direct communication
 * with OpenGL. This class loads the shader source code and then sends it to OpenGL
 * to compile it. It then sets up the Shader object and then passes it back.
 * @author Alex
 */
class ShaderLoader {

	private TextLoader fileLoader = new TextLoader("shader");
	
	protected ShaderLoader()	{ }
	
	protected Shader loadShader(String vertexShader, String fragmentShader)	{
		// Create a shader program and link the program to OpenGL
		int programID = glCreateProgram();
		int vShaderID = createShader(programID, 0, vertexShader, GL_VERTEX_SHADER);
		int fShaderID = createShader(programID, 0, fragmentShader, GL_FRAGMENT_SHADER);
		linkProgram(programID);
		
		// Return the shader
		return new Shader(programID, vShaderID, fShaderID);
	}
	
	private int createShader(int programID, int num, String path, int shader)	{
		// Read source code from the shader file
		String sourceCode = fileLoader.readTextFromFile(path);
		
		// Create a shader in OpenGL and import source code to the shader
		num = glCreateShader(shader);
		glShaderSource(num, sourceCode);
		
		// Compile the shader so that we can use it and check for errors
		glCompileShader(num);
		if(glGetShaderi(num, GL_COMPILE_STATUS) == GL_FALSE)
			throw new RuntimeException(glGetShaderInfoLog(num, glGetShaderi(
					num, GL_INFO_LOG_LENGTH)));
		
		// Bind the created shader to the shader program
		glAttachShader(programID, num);
		
		return num;
	}
	
	private void linkProgram(int programID)	{
		// Specify the attribute location (index)
		glBindAttribLocation(programID, 0, "position");
		glBindAttribLocation(programID, 1, "texture");
		
		// Link this shader program to OpenGL so that we can use it
		glLinkProgram(programID);
		
		// Check for errors
		if (glGetProgrami(programID, GL_LINK_STATUS) == GL_FALSE)
	        throw new RuntimeException("Unable to load the shader program!");
	}
}
