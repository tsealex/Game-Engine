package maths;

import java.nio.FloatBuffer;

import utils.BufferMaker;

public class Matrix {
	
	// Reference: https://solarianprogrammer.com/2013/05/22/opengl-101-matrices-projection-view-model/
	//            And also what I have learned in CS241 and Math209
	// OpenGL Matrix: col-major => cell[4] = first row, second col
	private float[] cell;
	
	public Matrix()	{
		cell = new float[16];
	}
	
	public Matrix(float[] array)	{
		cell = array;
	}
	
	public static Matrix getIdentity()	{
		Matrix idMtx = new Matrix();		
		idMtx.cell[0] = 1.0f;
		idMtx.cell[5] = 1.0f;
		idMtx.cell[10] = 1.0f;
		idMtx.cell[15] = 1.0f;
		return idMtx;
	}
	
	public static Matrix getScaling(float scaler)	{
		Matrix scalMtx = new Matrix();
		scalMtx.cell[0] = scaler;
		scalMtx.cell[5] = scaler;
		scalMtx.cell[10] = 1.0f;
		scalMtx.cell[15] = 1.0f;
		return scalMtx;
	}
	
	public static Matrix getScaling(Vector2D scalerVector)	{
		Matrix scalMtx = new Matrix();
		scalMtx.cell[0] = scalerVector.X();
		scalMtx.cell[5] = scalerVector.Y();
		scalMtx.cell[10] = 1.0f;
		scalMtx.cell[15] = 1.0f;
		return scalMtx;
	}
	
	public static Matrix getTranslation(Vector2D vector)	{
		Matrix transMtx = getIdentity();
		transMtx.cell[12] = vector.X();
		transMtx.cell[13] = vector.Y();
		return transMtx;
	}
	
	public static Matrix getTranslation(float transX, float transY)	{
		Matrix transMtx = getIdentity();
		transMtx.cell[12] = transX;
		transMtx.cell[13] = transY;
		return transMtx;
	}
	
	public static Matrix getRotation(double radian)	{
		// Rotate about z-axis in 3D space
		float sin = (float) Math.sin(radian);
		float cos = (float) Math.cos(radian);
		Matrix rotMtx = getIdentity();
		rotMtx.cell[0] = cos;
		rotMtx.cell[1] = sin;
		rotMtx.cell[4] = -sin;
		rotMtx.cell[5] = cos;
		return rotMtx;
	}
	
	public static Matrix getOrthoProjection(float right, float left, 
			float far, float near, float top, float bottom)	{
		Matrix opMtx = new Matrix();
		opMtx.cell[0] = 2.0f / (right - left);
		opMtx.cell[5] = 2.0f / (top - bottom);
		opMtx.cell[10] = - 2.0f / (far - near);
		opMtx.cell[12] = (right + left) / (left - right);
		opMtx.cell[13] = (top + bottom) / (bottom - top);
		opMtx.cell[14] = (far + near) / (near - far);
		opMtx.cell[15] = 1.0f;
		return opMtx;
	}
	
	public Matrix multiply(Matrix inMtx)	{
		return multiply(this, inMtx);
	}
	
	public static Matrix multiply(Matrix inMtx1, Matrix inMtx2)	{
		Matrix outMtx = new Matrix();
		for(int x = 0; x < 4; x++)	{
			int c = x * 4;
			for(int y = 0; y < 4; y++)	{
				outMtx.cell[c + y] = inMtx1.cell[y] * inMtx2.cell[c] + 
						inMtx1.cell[y + 4] * inMtx2.cell[c + 1] + 
						inMtx1.cell[y + 8] * inMtx2.cell[c + 2] + 
						inMtx1.cell[y + 12] * inMtx2.cell[c + 3];
			}
		}
		return outMtx;
	}
	
	public FloatBuffer toFloatBuffer()	{
		return BufferMaker.covertFloat(this.cell);
	}
	
	public static Matrix getTransformation(float x, float y, float rot, float scale)	{
		Matrix outMtx;
		if(scale >= 0.0001)	
			outMtx = getScaling(scale);
		else
			outMtx = getIdentity();
		if(!(Math.abs(rot) <= 0.0001))
			outMtx = outMtx.multiply(getRotation(rot));
		outMtx.cell[12] = x;
		outMtx.cell[13] = y;

		return outMtx;
	}
	
	public static Matrix getTransformationOpt(float x, float y, float rot, float scale)	{
		// Optimized
		float sin, cos;
		
		if(!(Math.abs(rot) <= 0.0001))	{
			sin = (float) Math.sin(rot) * scale;
			cos = (float) Math.cos(rot) * scale;
		} else	{
			sin = 0;
			cos = scale;
		}

		return new Matrix(new float[]	{
			cos, sin, 0, 0, - sin, cos, 0, 0, 0, 0, 1, 0, x, y, 0, 1	
		});
	}
}
