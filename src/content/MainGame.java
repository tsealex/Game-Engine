package content;

import content.objects.*;
import game.*;

public class MainGame extends AbstractGame {
	
	TestObj obj;
	Test2[] test;
	Test2[] test2;
	Test2[] test3;
	Background bg;

	@Override
	public void init() {
		// TODO Auto-generated method stub
		Effect().registerEffect(40, 40, 3, "ExplosionSheet.png");
		bg = new Background();
		obj = new TestObj();
		obj.init();
		test = new Test2[50];
		for(int x = 0; x < test.length; x++ )
			test[x] = new Test2(x*30-600,30 *(float)Math.pow(-1, x), "g.png");
		
		test2 = new Test2[50000];
		for(int x = 0; x < test2.length; x++ )
			test2[x] = new Test2(x*30-600,150+30 *(float)Math.pow(-1, x), "AlienPawn.png");
		
		test3 = new Test2[50000];
		for(int x = 0; x < test3.length; x++ )
			test3[x] = new Test2(x*30-600,-200+30 *(float)Math.pow(-1, x), "AlienPawn - Copy.png");
	
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		obj.update();
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		bg.draw();
		for(Test2 t : test)
			t.draw();
		for(Test2 t : test2)
			t.draw();
		
		for(Test2 t : test3)
			t.draw();
		obj.draw();
		Effect().drawEffect(1);
	}
	
	
}
