package gui;

import game.GameObject;

public class GUIpanel extends GameObject{
	Panel panelType;
	public GUIpanel(float xPlacement, float yPlacement, Panel panelType) {
		super(xPlacement, yPlacement, 10, 15, 0, 1.0f, false,  false, 9, "GUIpanel.png");
		this.panelType = panelType;
		deregisterPHXEngine();
	}

	@Override
	protected void Init() {	
	}
	
	@Override
	protected void Update() {
	}

	@Override
	protected void Draw() {	
		decidePanelType();
	}

	@Override
	public void action(int actionCode) {
	}

	private void decidePanelType(){
		if(panelType == Panel.TOP_LEFT_CORNER){
			setAnimation(0,0,1);
		} else if(panelType == Panel.TOP){
			setAnimation(1,1,1);
		}else if(panelType == Panel.TOP_RIGHT_CORNER){
			setAnimation(2,2,1);
		} else if(panelType == Panel.BOTTOM_LEFT_CORNER){
			setAnimation(3,3,1);
		} else if(panelType == Panel.BOTTOM){
			setAnimation(4,4,1);
		} else if(panelType == Panel.BOTTOM_RIGHT_CORNER){
			setAnimation(5,5,1);
		} else if(panelType == Panel.LEFT){
			setAnimation(6,6,1);
		} else if(panelType == Panel.FILL){
			setAnimation(7,7,1);
		} else if(panelType == Panel.RIGHT){
			setAnimation(8,8,1);
		} 
	}
	@Override
	public int getActionCode() {
		return -1;
	}

	@Override
	public String getPHXCategory() {
		return null;
	}
}
