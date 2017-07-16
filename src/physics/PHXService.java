package physics;

import graphics.GFXComponent;
import graphics.GFXDataCell;

public interface PHXService {
	public void register(PHXComponent newPHXObject);

	public void deregister(PHXComponent existingPHXObject);
	
	public void collidingWithCallback(PHXComponent PHXObject, String categoryName);
	
	public void collidingCallback(PHXComponent PHXObject);
	
	public boolean isCollidingWith(PHXComponent PHXObject, String categoryName);
	
	public boolean isColliding(PHXComponent PHXObject);
}
