package engine;

import java.util.Random;

import utils.FileLoader;

public class Config {

	private static int Scrn_HEIGHT = 720;
	private static int Scrn_WIDTH = 1280;
	private static float Scrn_HEIGHT_SCALE = 2 / (float) Scrn_HEIGHT;
	private static float Scrn_WIDTH_SCALE = 2 / (float) Scrn_WIDTH;
	public static final int REFRESH_RATE = 1;
	
	public final static int UPDATE_RATE = 60;
	public final static String GAME_TITLE = "StrategyGame";
	
	public static int ScrnHeight()	{ return Scrn_HEIGHT; }
	public static int ScrnWidth()	{ return Scrn_WIDTH; }

	public static float ScrnHeightScale()	{ return Scrn_HEIGHT_SCALE; }
	public static float ScrnWidthScale()	{ return Scrn_WIDTH_SCALE; }
	
	public static final Random RANDOM_GEN = new Random();
}
