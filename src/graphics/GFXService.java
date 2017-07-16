package graphics;

public interface GFXService {
	public void register(GFXComponent obj);
	
	public void register(GFXDataCell cell, String identiy);

	public void deregister(GFXComponent obj);
	
	public void requestForRender(GFXDataCell cell);
}
