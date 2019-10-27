import org.newdawn.slick.Input;

/**
 * Implement and define the behavior of the player laser and 
 * provide a concrete implementation of update
 */
public class PlayerLaser extends LaserShot {
	public static final String PATH = "res/shot.png";
	public static final float PLAYER_LASER_SPEED = 3f;

	/**
	 * Constructor
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 */
	public PlayerLaser(float x, float y) {
		super(PATH, x, y);
	}
	
	/* (non-Javadoc)
	 * @see LaserShot#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
		//only do vertical movement
		move(0, -delta*PLAYER_LASER_SPEED);
		
		if(!onScreen()) {
			deactivate();
		}
	}

}
