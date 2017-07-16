package utils;

import java.net.URL;

public class FileLoader {
	
	private String folder;
	
	public FileLoader()	{ }
	
	public FileLoader(String folder)	{ 
		this.folder = "/" + folder;
	}
	
	public URL getFileURL(String path)	{
		return getClass().getResource(folder + "/" + path);
	}
}
