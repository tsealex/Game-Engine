package content.objects;

import java.util.Random;

import engine.InputManager;
import engine.InputManager.Key;
import game.GameObject;

public class Genie extends GameObject{
	private float lastX;
	private float lastY;
	private boolean leftWalking;
	private boolean rightWalking;
	private boolean downWalking;
	private boolean upWalking;
	private boolean walking;
	private double randWalkingDirection;
	private float walkingSpeed;
	private int frameCounter;
	public Genie(float startX, float startY){
		super( startX, startY, 32, 32, 0, 1, false, false, 12, "Genie.png");
		leftWalking = false;
		rightWalking = false;
		walkingSpeed = 0.5f;
		frameCounter = 0;
		lastX = 0;
		lastY = 0 ;
	}
	
	@Override
	protected void Init() {
	}

	@Override
	protected void Update() {
		lastX = this.X();
		lastY = this.Y();
		Random rand = new Random();
		walking = false;
		if(frameCounter >= 60){
			randWalkingDirection = Math.round(rand.nextDouble()*100);
			frameCounter = 0;
		}
		if(randWalkingDirection<25){
			this.X(this.X() - walkingSpeed);
			leftWalking = true;
			rightWalking = false;
			upWalking = false;
			downWalking = false;
			walking = true;
		}
		if (randWalkingDirection >= 25 && randWalkingDirection < 50){
			this.X(this.X() + walkingSpeed);
			rightWalking = true;
			leftWalking = false;
			upWalking = false;
			downWalking = false;
			walking = true;
		}
		if (randWalkingDirection >= 50 && randWalkingDirection < 75){
			this.Y(this.Y() + walkingSpeed);
			leftWalking = false;
			rightWalking = false;
			upWalking = true;
			downWalking = false;
			walking = true;
		}
		if (randWalkingDirection >= 75){ 
			this.Y(this.Y() - walkingSpeed);
			leftWalking = false;
			rightWalking = false;
			upWalking = false;
			downWalking = true;
			walking = true;
		}
		frameCounter++;
		if(isCollidingWith("Orc")){
			this.X(lastX);
			this.Y(lastY);
		}
		
	}

	@Override
	protected void Draw() {
		//LEFT
		if(leftWalking && walking){
			setAnimation(9, 11, 8);
		} else if(leftWalking && !walking){
			setAnimation(10, 10, 5);
		} 
		
		//RIGHT
		else if(rightWalking && walking){
			setAnimation(3, 5, 8);
		} else if(rightWalking && !walking){
			setAnimation(4, 4, 5);
		} 
		
		//DOWN
		else if(downWalking && walking){
			setAnimation(0, 2, 8);
		} else if(downWalking && !walking){
			setAnimation(1, 1, 5);
		} 
		//UP
		else if(upWalking && walking){
			setAnimation(6, 8, 8);
		} else if(upWalking && !walking){
			setAnimation(7, 7, 5);
		}
		
	}

	@Override
	public void action(int actionCode) {
	}

	@Override
	public int getActionCode() {
		return 102;
	}

	@Override
	public String getPHXCategory() {
		return "Genie";
	}



}
