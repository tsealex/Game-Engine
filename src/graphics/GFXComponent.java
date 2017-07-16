package graphics;

import engine.GEService;

/**
 * GEService interface enables the communication between a GameObject and the
 * GraphicsManager. Thus, GraphicsManager can get the rendering information of
 * the object and share it to Renderer, which, in further, instructs the OpenGL
 * program how to draw.
 * 
 * @author Alex
 */
public interface GFXComponent {
	// Registration methods
	public void registerGFXEngine(String path);

	public void deregisterGFXEngine();

	// Object that contains graphics information
	public void setGFXCell(GFXDataCell gfxCell);

	public GFXDataCell getGFXCell();

	// Through this method, make the object update its DataCell
	public void updateDataCell();

	// Drawing method
	public void draw();

	// Use this to determine object is to be displayed
	public boolean Drawable();

}
