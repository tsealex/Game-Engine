package game;

public interface EffectService {
	public int registerEffect(int width, int height, int regNum, String path);

	public void requestForEffect(EffectRequest request, int listNumber);

	public void drawEffect(int listNumber);
}
