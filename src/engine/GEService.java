package engine;

import physics.PHXService;
import game.AbstractGame;
import game.EffectService;
import game.SpaceManager;
import game.SpaceService;
import graphics.GFXService;

public interface GEService {
	public GFXService Graphics();

	public PHXService Physics();

	public SpaceService GameSpace();
	
	public EffectService Effect();
}
