package content;

import content.objects.Genie;
import content.objects.Orc;
import content.objects.Skeleton;
import content.objects.Planet;
import game.AbstractGame;
import gui.GUIcharacter;
import gui.GUImessage;
import gui.GUIpanel;
import gui.Panel;

public class MitchellTestGame extends AbstractGame{
	Skeleton skelly;
	Orc greenie;
	Orc greenieBrother;
	Genie malz;
	GUIcharacter char1;
	GUIpanel fill;
	GUImessage message;
	PlanetsInitializer pinit;
	Planet planet;
	@Override
	public void init() {
		skelly = new Skeleton();
		skelly.init();
		greenie = new Orc(100,0);
		greenie.init();
		greenieBrother = new Orc(-100,0);
		greenieBrother.init();
		malz = new Genie(0, 100);
		malz.init();
		
		message = new GUImessage("TESTAAAAAAAAAABBBBAAAAAAAAAAAA", 100,-200);
		message.initialize();
		pinit = new PlanetsInitializer("data");
		planet = new Planet(-200, 200, 205, "Planet.png");
	}

	@Override
	public void update() {
		skelly.update();
		greenie.update();
		greenieBrother.update();
		malz.update();
		pinit.update();
	}

	@Override
	public void draw() {
		planet.draw();
		pinit.draw();
		greenie.draw();
		greenieBrother.draw();
		skelly.draw();
		malz.draw();
		message.draw();
	}

}
