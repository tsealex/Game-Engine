package engine;

public class GameClock {

	// Get incremented by one every update loop
	private static int time;
	
	protected GameClock(GameEngine engine)	{ }

	public static int Time() {
		return time;
	}
	
	protected void incrTime()	{
		time++;
	}
}
