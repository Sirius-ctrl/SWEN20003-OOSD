import org.newdawn.slick.Input;

/**
 * This class defines and implements the behaviors of any enemy laser
 * shot.
 */
public class EnemyLaser extends LaserShot {
	/** the enemy laser graph path */
	public static final String PATH = "res/enemy-shot.png";
	
	private static final float SPEED = 0.7f;

	/**
	 * Constructor
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 */
	public EnemyLaser(float x, float y) {
		super(PATH, x, y);
	}

	/* (non-Javadoc)
	 * @see LaserShot#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {	
		move(0, SPEED*delta);
		
		if(!onScreen()) {
			deactivate();
		}
		
	}

}
