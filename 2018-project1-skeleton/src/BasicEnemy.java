import org.newdawn.slick.Input;


/**
 * This class define and implement the behavior of Basic enemy including
 * how it is moving.
 */
public class BasicEnemy extends Enemy {
	/** Basic enemy graph path */
	public static final String PATH = "res/basic-enemy.png";
	
	private static final float BASIC_SPEED = 0.3f;
	private static final int SCORE = 50;
	private static final int LIFE = 1;
	
	private final int DELAY;

	
	/**
	 * Constructor
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 * @param delay the time in millisecond should past before it starting to moves into the screen
	 */
	public BasicEnemy(float x, float y, int delay) {
		super(PATH, x, y, SCORE, LIFE, delay);
		this.DELAY = delay;
	}
	
	
	/* (non-Javadoc)
	 * @see Enemy#update(org.newdawn.slick.Input, int)
	 */
	public void update(Input input, int dealt) {
		if(World.getInstance().getTimer() >= DELAY) {
			move(0, dealt*BASIC_SPEED);
		}
	}
	
}
