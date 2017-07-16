package engine;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import content.MainGame;
import content.MitchellTestGame;
import physics.PHXService;
import physics.PhysicsEngine;
import game.AbstractGame;
import game.Camera;
import game.EffectManager;
import game.EffectService;
import game.SpaceManager;
import game.GameObject;
import game.SpaceService;
import graphics.GFXService;
import graphics.GraphicsEngine;

/**
 * GameEngine class is the entry point of the program. It maintains the integrity
 * of the game engine. It employs GraphicsManager to render and draw the images.
 * @author Alex
 */
public class GameEngine implements Runnable, GEService {

	private static boolean isRunning = false;
	
	private int FPS;
	private long workTime, runningTime;
	private long frameStartTime;
	private long updateDelay;
	private int[] gfxData = new int[]	{0, 0, 0};
	
	private GraphicsEngine gfxEngine;
	private PhysicsEngine phxEngine;
	private InputManager inManager;
	
	private GameClock gameClock;
	private Camera gameCamera;
	private AbstractGame game;
	private EffectManager effectManager;
	private SpaceManager spaceManager;
	
	private GameEngine()	{
		// Use multiple threads to run this game in order to get rendering and
		// drawing separated from the fixed-rated main game loop
		updateDelay = 1000 / Config.UPDATE_RATE;
		inManager = new InputManager(this);
		gameClock = new GameClock(this);
		gameCamera = new Camera(this);
		spaceManager = new SpaceManager(this);
		game = new MitchellTestGame();
		
		gfxEngine = new GraphicsEngine(this, inManager);
		phxEngine = new PhysicsEngine();
		effectManager = new EffectManager(this);

		this.init();
		isRunning = true;
	}
	
	public void init()	{
		gfxEngine.init();
		game.setGameService(this);
	}
	
	public static void main(String[] args)	{
		// Entry point of the program
		if(!isRunning)	{ 
			new GameEngine().run();
		}
	}
	
	public void run()	{
		long temp = System.currentTimeMillis();
		game.init();
		System.out.println("Initializaion takes " + (System.currentTimeMillis() - temp) + " ms");
		// Main game loop
		frameStartTime = System.currentTimeMillis();
		gfxEngine.handleInput();
		
		spaceManager.findUpdatableObjects();
		spaceManager.findDrawableObjects();
		
		while(glfwWindowShouldClose(gfxEngine.WindowID()) == GL_FALSE)	{
			gfxEngine.renderBegin();
			game.draw();
			gfxEngine.renderEnd();
			
			gameCamera.update();
			FPS++;
			workTime = System.currentTimeMillis() - frameStartTime;	
			if(workTime >= updateDelay)	{
				runningTime += workTime;
				frameStartTime = System.currentTimeMillis();
				preupdate();
				game.update();
				displayRunningState();
			}
		}
		
		gfxEngine.end();
	}
	
	private void preupdate()	{
		if(GameClock.Time() % 60 == 0)	{
			// Every four seconds, this will determine which objects should be updated or not
			spaceManager.findUpdatableObjects();
			// Every four seconds, this will determine which objects should be drawn or not
			spaceManager.findDrawableObjects();
		}
	}

	private void displayRunningState()	{
		gameClock.incrTime();
		gfxData = gfxEngine.getEngineData();
		if(runningTime >= 1000)	{
			System.out.printf("FPS: %4d, Memory Usage: %d, Drawn Objects: %d, "
					+ "VAO Number: %d, Loaded Textures: %d \n", FPS, 
					(int) getMemoryUsage(), gfxData[0], gfxData[1], gfxData[2]);
			FPS = 0;
			runningTime = 0;
		}
	}
	
	private long getMemoryUsage()	{ 
		return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(); 
	}
	
	public void stop() {
		isRunning = false;
	}
	
	public GFXService Graphics()	{
		return gfxEngine;
	}

	public PHXService Physics() {
		return phxEngine;
	}
	
	public SpaceService GameSpace()	{
		return spaceManager;
	}
	
	public EffectService Effect()	{
		return effectManager;
	}
}
