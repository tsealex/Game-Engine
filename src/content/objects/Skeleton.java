package content.objects;

import engine.InputManager;
import engine.InputManager.Key;
import game.Camera;
import game.GameObject;
import gui.GUImessage;

public class Skeleton extends GameObject{
	private boolean walkingSlowed;
	private float lastX;
	private float lastY;
	private float lastCamX;
	private float lastCamY;
	private boolean leftWalking;
	private boolean rightWalking;
	private boolean downWalking;
	private boolean upWalking;
	private boolean walking;
	private float walkingSpeed;
	public Skeleton(){
		super( 0, 0, 32, 32, 0, 1, false, false, 12, "Skeleton.png");
		leftWalking = false;
		rightWalking = false;
		walkingSpeed = 3; 
		lastX = 0;
		lastY = 0;
		lastCamX = 0;
		lastCamY = 0;
		walkingSlowed = false;
	}
	
	@Override
	protected void Init() {
	}

	@Override
	protected void Update() {
		lastX = this.X();
		lastY = this.Y();
		walking = false;
		if(InputManager.contain(Key.A)){
			this.X(this.X() - walkingSpeed);
			leftWalking = true;
			rightWalking = false;
			upWalking = false;
			downWalking = false;
			walking = true;
			Camera.shift(-walkingSpeed, 0);
			lastCamX += walkingSpeed;
		}
		if (InputManager.contain(Key.D)){
			this.X(this.X() + walkingSpeed);
			rightWalking = true;
			leftWalking = false;
			upWalking = false;
			downWalking = false;
			walking = true;
			Camera.shift(walkingSpeed, 0);
			lastCamX -= walkingSpeed;
		}
		if (InputManager.contain(Key.W)){
			this.Y(this.Y() + walkingSpeed);
			leftWalking = false;
			rightWalking = false;
			upWalking = true;
			downWalking = false;
			walking = true;
			Camera.shift(0, walkingSpeed);
			lastCamY -= walkingSpeed;
		}
		if (InputManager.contain(Key.S)){ 
			this.Y(this.Y() - walkingSpeed);
			leftWalking = false;
			rightWalking = false;
			upWalking = false;
			downWalking = true;
			walking = true;
			Camera.shift(0, -walkingSpeed);
			lastCamY += walkingSpeed;
		}
		
		if(isCollidingWith("Orc")){
			this.X(lastX);
			this.Y(lastY);
			Camera.shift(lastCamX, lastCamY);
		}
		lastCamY = 0;
		lastCamX = 0;
		collidesWithCallback("Genie");
	}

	@Override
	protected void Draw() {
		//LEFT
		if(leftWalking && walking){
			setAnimation(9, 11, 5);
		} else if(leftWalking && !walking){
			setAnimation(10, 10, 5);
		} 
		
		//RIGHT
		else if(rightWalking && walking){
			setAnimation(3, 5, 5);
		} else if(rightWalking && !walking){
			setAnimation(4, 4, 5);
		} 
		
		//DOWN
		else if(downWalking && walking){
			setAnimation(0, 2, 5);
		} else if(downWalking && !walking){
			setAnimation(1, 1, 5);
		} 
		//UP
		else if(upWalking && walking){
			setAnimation(6, 8, 5);
		} else if(upWalking && !walking){
			setAnimation(7, 7, 5);
		}
	}

	@Override
	public void action(int actionCode) {		
		if(actionCode == 102){
		}
	}

	@Override
	public int getActionCode() {
		return 0;
	}

	@Override
	public String getPHXCategory() {
		return "Skeleton";
	}



}
