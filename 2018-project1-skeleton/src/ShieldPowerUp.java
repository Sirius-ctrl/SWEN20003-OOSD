/**
 * This class only define the basic image path and generated point for
 * a Shield boost for the player.
 */
public class ShieldPowerUp extends PowerUp {
	/** the shield powerup graph path */
	public static final String PATH = "res/shield-powerup.png";
	
	/**
	 * Constructor
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 */
	public ShieldPowerUp(float x, float y) {
		super(PATH, x, y);
	}

}
