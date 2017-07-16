package game;

import engine.*;
import game.SpaceManager.Position;
import graphics.*;
import physics.*;

public abstract class GameObject implements PHXComponent, GFXComponent {
	private static GEService geService;
	private static GFXService gfxService;
	private static PHXService phxService;
	private static SpaceService gmSpace;
	private static EffectService effectService;
	
	private Position pos;
	private int regNum;
	private int width, height;
	private float rotation, scale;
	private boolean rgstrGFX, rgstrPHX;
	private boolean detectable, interactable;
	private boolean moved = true;
	
	private GFXDataCell gfxData;
	
	public GameObject(float x, float y, int width, int height, float rotation, float scale,
			boolean detectable, boolean interactable, int regNum, String path) {
		pos = gmSpace.registerPosition(x, y);
		this.width = width;
		this.height = height;
		this.rotation = rotation;
		this.scale = scale;
		this.detectable = detectable;
		this.interactable = (interactable && detectable);
		this.regNum = regNum;
		registerGFXEngine(path);
		registerPHXEngine();
	}

	public void init()	{
		Init();
	}
	
	protected abstract void Init();
	
	public void update()	{
		if(!Updatable())
			return;
		
		Update();
	}
	
	protected abstract void Update();
	
	public void draw()	{
		if(!Drawable() || !rgstrGFX)
			return;
		
		Draw();
		updateDataCell();
		gfxService.requestForRender(gfxData);
	}
	
	protected abstract void Draw();
	
	public void destroy()	{
		deregisterGFXEngine();
		deregisterPHXEngine();
		gmSpace.removePosition(pos);
		gfxData = null;
		pos = SpaceManager.BLACK_HOLE;
	}
	
	protected static final void setEngine(GameEngine engine)	{
		geService = engine;
		gfxService = geService.Graphics();
		phxService = geService.Physics();
		gmSpace = geService.GameSpace();
		effectService = geService.Effect();
	}

	@Override
	public void registerGFXEngine(String path) {
		if(rgstrGFX)
			return;
		if(regNum > 1)
			gfxData = new GFXDataCell(width, height, regNum, path);
		else
			gfxData = new GFXDataCell(width, height, path);
		path = null;
		gfxService.register(this);
		rgstrGFX = true;
	}

	@Override
	public void deregisterGFXEngine() {
		if(!rgstrGFX)
			return;
		gfxService.deregister(this);
		rgstrGFX = false;
	}

	@Override
	public void setGFXCell(GFXDataCell gfxCell) {
		this.gfxData = gfxCell;
	}

	@Override
	public GFXDataCell getGFXCell() {
		return gfxData;
	}

	@Override
	public void updateDataCell() {
		if(Camera.Shifted() || moved)	{
			gfxData.setAttribute(pos.X(), pos.Y(), rotation, scale);
			moved = false;
		}
	}

	@Override
	public boolean Drawable() {
		return pos.Drawable();
	}

	@Override
	public void registerPHXEngine() {
		if(rgstrPHX)
			return;
		phxService.register(this);
		rgstrPHX = true;
	}

	@Override
	public void deregisterPHXEngine() {
		if(!rgstrPHX)
			return;
		phxService.deregister(this);
		rgstrPHX = true;
	}

	@Override
	public float Rotation() {
		return rotation;
	}

	@Override
	public float Scale() {
		return scale;
	}

	@Override
	public int Height() {
		return height;
	}

	@Override
	public int Width() {
		return width;
	}

	@Override
	public float X() {
		return pos.X();
	}
	
	public void X(float x)	{
		moved = true;
		pos.X(x);
	}
	
	public void changeX(float x)	{
		moved = true;
		pos.changeX(x);
	}

	@Override
	public float Y() {
		return pos.Y();
	}
	
	public void Y(float y)	{
		moved = true;
		pos.Y(y);
	}
	
	public void changeY(float y)	{
		moved = true;
		pos.changeY(y);
	}

	@Override
	public boolean Updatable() {
		return pos.Updatable();
	}

	@Override
	public boolean Detectable() {
		return detectable;
	}

	@Override
	public void Detectable(boolean detectable) {
		this.detectable = detectable;
	}

	@Override
	public boolean Interactable() {
		return interactable;
	}

	@Override
	public void Interactable(boolean interactable) {
		this.interactable = interactable;
	}
	
	public void setAnimation(int min, int max, int frame)	{
		gfxData.setAnimation(min, max, frame);
	}
	
	public EffectService Effect()	{
		return effectService;
	}
	
	/**
	 * Not recommended, as it checks for collision with every single object in the game
	 * @return
	 */
	protected boolean isColliding() {
		return phxService.isColliding(this);
	}
	
	protected boolean isCollidingWith(String categoryOfPHXObject) {
		return phxService.isCollidingWith(this, categoryOfPHXObject);
	}
	
	protected void collidesWithCallback(String categoryOfPHXObject){
		phxService.collidingWithCallback(this, categoryOfPHXObject);
	}
	
	/**
	 * Not recommended, as it checks for collision with every single object in the game
	 */
	protected void collidesCallback(){
		phxService.collidingCallback(this);
	}
}
