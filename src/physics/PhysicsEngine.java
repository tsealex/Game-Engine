package physics;

// -Mitch, I still have a lot to do here, and I need to set up the service so I can register and deregister objects
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PhysicsEngine implements PHXService{
	HashMap<String, List<PHXComponent>> PHXObjects;
	
	public PhysicsEngine(){
		PHXObjects = new HashMap<String, List<PHXComponent>>();
	}
	
	/**
	 * Determines if a collision occurred using simple AABB hitbox collision
	 * @param A
	 * @param B
	 * @return Returns TRUE if a collision happened, FALSE if no collision happened.
	 */
	private boolean checkCollision(PHXComponent A, PHXComponent B){
		/*
		 * We start by assuming that a collision has happened, and unless proven otherwise we will stick with this.
		 */
		boolean collisionOccurred = true;
		/*
		 * CHECK ONE
		 * 
		 * [ ]   |   [ ]   Is box A left of box B? Or is box B left of box A? If so we will return false
		 */
		if(A.X() + A.Width() < B.X()){
			return false;
		}
		if(B.X() + B.Width() < A.X()){
			return false;
		}
		
		/*
		 * CHECK TWO
		 * [ ]
		 * 
		 * ___    Is box A above box B? Or is box B above A? If so we will return false
		 * 
		 * [ ]
		 */
		if(A.Y() - A.Height() > B.Y()){
			return false;
		}
		if(B.Y() - B.Height() > A.Y()){
			return false;
		}
		//If none of the above could be proven, then we must assume that they are overlapping and a collision has occurred.
		return collisionOccurred;
	}
	@Override
	public void register(PHXComponent newPHXObject) {	
		String PHXObjectCategory = newPHXObject.getPHXCategory();
		if(PHXObjects.containsKey(PHXObjectCategory)){
			PHXObjects.get(PHXObjectCategory).add(newPHXObject);
		} else {
			PHXObjects.put(PHXObjectCategory, new ArrayList<PHXComponent>());
			PHXObjects.get(PHXObjectCategory).add(newPHXObject);
		}
	}

	@Override
	public void deregister(PHXComponent existingPHXObject) {
		String PHXObjectCategory = existingPHXObject.getPHXCategory();
		try{
			PHXObjects.get(PHXObjectCategory).remove(existingPHXObject);
		} catch (Exception e){
			System.err.println("Error deregistering! \n Selected object of type \"" + PHXObjectCategory + "\" was never registered with the physics engine!");
		}
	}
	//TODO: Callback not tested yet
	@Override
	public void collidingWithCallback(PHXComponent PHXObject, String categoryName) {
		for(int i = 0; i < PHXObjects.get(categoryName).size() ; i++){
			if(checkCollision(PHXObject, PHXObjects.get(categoryName).get(i))){
				if(PHXObject.equals(PHXObjects.get(categoryName).get(i))){
				} else {
					int actionCode1 = PHXObject.getActionCode();
					int actionCode2 = PHXObjects.get(categoryName).get(i).getActionCode();
					PHXObject.action(actionCode2);
					PHXObjects.get(categoryName).get(i).action(actionCode1);
				}
			}
		}
	}
	//TODO: Callback not tested yet
	@Override
	public void collidingCallback(PHXComponent PHXObject) {
		for (List<PHXComponent> category : PHXObjects.values()) {
			for(int i = 0 ; i < category.size(); i++){
				if(checkCollision(PHXObject, category.get(i))){
					if(PHXObject.equals(category.get(i))){
					} else {
						int actionCode1 = PHXObject.getActionCode();
						int actionCode2 = category.get(i).getActionCode();
						PHXObject.action(actionCode2);
						category.get(i).action(actionCode1);
					}
				}
			}
		}
	}

	@Override
	public boolean isCollidingWith(PHXComponent PHXObject, String categoryName) {
		for(int i = 0; i < PHXObjects.get(categoryName).size() ; i++){
			if(checkCollision(PHXObject, PHXObjects.get(categoryName).get(i))){
				if(PHXObject.equals(PHXObjects.get(categoryName).get(i))){
				} else {
				return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean isColliding(PHXComponent PHXObject) {
		for (List<PHXComponent> category : PHXObjects.values()) {
			for(int i = 0 ; i < category.size(); i++){
				if(checkCollision(PHXObject, category.get(i))){
					if(PHXObject.equals(category.get(i))){
					} else {
					return true;
					}
				}
			}
		}
		return false;
	}	
}
