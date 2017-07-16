package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageLoader extends FileLoader {
	
	public ImageLoader(String folder)	{
		super(folder);
	}
	
	public BufferedImage getBufferedImage(String path)	{
		URL url = getFileURL(path);
		try {
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
