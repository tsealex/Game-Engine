package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextLoader extends FileLoader {

	public TextLoader(String folder)	{
		super(folder);
	}
	
	public String readTextFromFile(String path)	{
		// Use StringBuilder because it would consume less memory in compared to
		// using String object when reading huge text files
		StringBuilder returnString = new StringBuilder();
		
		try {
			// Read from the text file
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getFileURL(path).openStream()));
			
			// Construct a larger string
			String temp;
	        while ((temp = reader.readLine()) != null) 
	        	returnString.append(temp).append("\n");
	        reader.close();
	        
		} catch (IOException e) {
			System.out.println("Fail to load file: " + path);
			e.printStackTrace();
		}
		
		return returnString.toString();
	}
}
