
/**
 * This class only define the basic image path and generated point for
 * a shooting speed up boost for the player.
 */
public class ShootSpeedUp extends PowerUp {
	/** the shoot speed up powerup graph path */
	public static final String PATH = "res/shotspeed-powerup.png";

	/**
	 * Constructor
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 */
	public ShootSpeedUp(float x, float y) {
		super(PATH, x, y);
	}

}
