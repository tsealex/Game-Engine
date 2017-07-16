package maths;

public class Range {
	
	private int max, min;
	
	public Range(int maximum, int minimum)	{
		if(minimum > maximum)	{
			int temp = maximum;
			maximum = minimum;
			minimum = temp;
		}
		
		max = maximum;
		min = minimum;
	}

	public int getMax() {
		return max;
	}

	public int getMin() {
		return min;
	}

	public boolean isInRange(int number)	{
		return (number >= min && number < max);
	}
}
