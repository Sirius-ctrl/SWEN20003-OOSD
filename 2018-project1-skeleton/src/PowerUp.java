import org.newdawn.slick.Input;

/**
 *The superclass of the any power up in this game world
 */
public class PowerUp extends Sprite {
	
	private static final float SPEED = 0.1f;

	/**
	 * @param imageSrc	The image path of the power up
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 */
	public PowerUp(String imageSrc, float x, float y) {
		super(imageSrc, x, y);
	}

	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
		// move down the screen
		move(0, SPEED*delta);
		
		if(!onScreen()) {
			deactivate();
		}
	}
	   
}
