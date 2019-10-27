import org.newdawn.slick.Input;

/**
 * This class implements and define the behavior for sine enemy
 */
public class SineEnemy extends Enemy {
	/** the sine enemy graph path */
	public static final String PATH = "res/sine-enemy.png";
	
	private static final int SCORE = 100;
	private static final float SPEED = 0.15f;
	private static final float AMPLITUDE = 96;
	private static final float PERIOD = 1500;
	private static final int LIFE = 1;
	
	// store the last change of the sine function
	private float pre = 0;
	private final int DELAY;
	
	/**
	 * Constructor
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 * @param delay waiting time till it starting to moving down the screen
	 */
	public SineEnemy(float x, float y, int delay) {
		super(PATH, x, y, SCORE, LIFE, delay);
		this.DELAY = delay;
	}
	

	/* (non-Javadoc)
	 * @see Enemy#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
		// create SineEnemy when reach the delay time
		if(World.getInstance().getTimer() >= DELAY) {
			//handle vertical movement
			float dy = SPEED;
			
			//handle horizontal movement
			float dx = (float) (AMPLITUDE*Math.sin((2*Math.PI*(World.getInstance().getTimer()-DELAY)/PERIOD)));
			
			// implement the changes
			move(dx-pre, dy*delta);
			
			pre = dx;
		}
	}

}
