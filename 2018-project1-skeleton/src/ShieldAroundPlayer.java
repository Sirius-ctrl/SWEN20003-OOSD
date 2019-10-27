import org.newdawn.slick.Input;

/**
 * This class keep track the position of the player and render
 * a shield around it when the shield is activated
 */
public class ShieldAroundPlayer extends Sprite {
	/** the shield that around player graph path */
	public final static String PATH = "res/shield.png";
	
	/**
	 * Constructor
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 */
	public ShieldAroundPlayer(float x, float y) {
		super(PATH, x, y);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#moveTo(float, float)
	 */
	@Override
	public void moveTo(float x, float y) {
		super.moveTo(x, y);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
	}

}
