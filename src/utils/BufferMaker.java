package utils;

import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class BufferMaker {
	
	private BufferMaker()	{ }
	
	public static ByteBuffer convertByte(byte[] array)	{
		ByteBuffer buffer = BufferUtils.createByteBuffer(array.length);
		buffer.put(array).flip();
		return buffer;
	}
	
	public static IntBuffer convertInt(int[] array)	{
		IntBuffer buffer = BufferUtils.createIntBuffer(array.length);
		buffer.put(array).flip();
		return buffer;
	}
	
	public static FloatBuffer covertFloat(float[] array)	{
		FloatBuffer buffer = BufferUtils.createFloatBuffer(array.length);
		buffer.put(array).flip();
		return buffer;
	}

	public static IntBuffer convertImage(BufferedImage temp)	{
		// Create an integer array that stores the color of each pixel
		int totalPixels = temp.getWidth() * temp.getHeight();
		int[] imgPixels = new int[totalPixels];
		imgPixels = temp.getRGB(0, 0, temp.getWidth(), temp.getHeight(), null, 0, temp.getWidth());
		
		// Extract and rearrange the integer buffer in order to enable OpenGL 
		// for further using the buffer data
		int[] buffer = new int[totalPixels];
		int i;
		for(int y = 0; y < temp.getHeight(); y++)	{
			for(int x = 0; x < temp.getWidth(); x++)	{
				// Isolate the bits and arrange them in the A-B-G-R order
				// Shift the binary digit to the right to get its value
				i = x + y * temp.getWidth();
				int a = (imgPixels[i] & 0xff000000) >> 24;
				int r = (imgPixels[i] & 0xff0000) >> 16;
				int g = (imgPixels[i] & 0xff00) >> 8;
				int b = (imgPixels[i] & 0xff);
				
				buffer[temp.getWidth() * (temp.getHeight() - 1 - y) + x] = a << 24
						| b << 16 | g << 8 | r;
			}
		}
		
		// Convert the array to buffer then return
		return convertInt(buffer);
	}
}
