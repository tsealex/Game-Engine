package physics;

import java.util.ArrayList;
import java.util.List;

/**
 * If an object implements this interface, it can then be handled by the physics engine.
 * @author Mitch_000
 *
 */
public interface PHXComponent {
	
	/**
	 * Registers itself to the physics engine
	 */
	public void registerPHXEngine();

	/**
	 * Removes itself from the physics engine
	 */
	public void deregisterPHXEngine();

	/**
	 * Provides this object's current rotation.
	 * @return Rotation as float.
	 */
	public float Rotation();

	/**
	 * Provides this object's current scale.
	 * @return Scale as float.
	 */
	public float Scale();

	/**
	 * Provides the height of the current object.
	 * @return Height as a float.
	 */
	public int Height();

	/**
	 * Provides the width of the current object.
	 * @return Width as a float.
	 */
	public int Width();

	/**
	 * Provides the x position of the current Object. (Top Left coordinate of bounding box)
	 * @return X position as an integer.
	 */
	public float X();

	/**
	 * Provides the y position of the current Object. (Top Left coordinate of bounding box)
	 * @return Y position as an integer.
	 */
	public float Y();

	/**
	 * This object can be updated, and has functionality that will be executed routinely.
	 */
	public void update();

	/**
	 * Returns true if this object can be updated, and false if it cannot.
	 * @return Is Updatable? TRUE/FALSE
	 */
	public boolean Updatable();

	/**
	 * Returns true if this object can be detected, and false if it cannot.
	 * @return Is Detectable? TRUE/FALSE
	 */
	public boolean Detectable();

	/**
	 * Sets this object to be detectable if true, and not detectable if false.
	 * @param detectable
	 */
	public void Detectable(boolean detectable);

	/**
	 * Returns true if this object can be interacted with, and false if it cannot.
	 * @return Is Interactable? TRUE/FALSE
	 */
	public boolean Interactable();

	/**
	 * Set this object to be interactable if true, and not interactable if false.
	 * @param interactable
	 */
	public void Interactable(boolean interactable);
	
	/**
	 * Activate this object's action method, so it can respond to some event appropriately.
	 * @param actionCode
	 */
	public void action(int actionCode);
	
	/**
	 * Returns the action code of this object, generally so it can be passed to another object that it collided with.
	 * @return actionCode
	 */
	public int getActionCode();
	
	/**
	 * Returns the category of this object, so the physics engine can appropriately identify it by its type.
	 * Example: If you list this object as "Dirt", it will be placed in the physics engine under the dirt category.
	 * If another object needs to check if it collides with "Dirt" it can simply check against this active instances of this type.
	 * @return Category Name
	 */
	public String getPHXCategory();
}
