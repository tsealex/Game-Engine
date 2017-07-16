package game;

import engine.*;

public abstract class AbstractGame {

	private GEService geService;
	private EffectService effect;
	
	public AbstractGame() {

	}
	
	public void setGameService(GameEngine engine)	{
		geService = engine;
		effect = engine.Effect();
		GameObject.setEngine(engine);
	}

	public abstract void init();

	public abstract void update();

	public abstract void draw();

	public void halt() {

	}

	public void reinit() {

	}
	
	protected GEService getService()	{
		return geService;
	}
	
	public EffectService Effect()	{
		return effect;
	}
}
