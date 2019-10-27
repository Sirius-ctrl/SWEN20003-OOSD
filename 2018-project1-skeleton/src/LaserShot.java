import org.newdawn.slick.Input;

/**
 * This abstract class only built for extending, any laser
 * should extend this class.
 */
public abstract class LaserShot extends Sprite {
	
	/**
	 * Constructor
	 * @param path the image path of the laser shot
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 */
	public LaserShot(String path, float x, float y) {
		super(path, x, y);
	}
	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public abstract void update(Input input, int delta);

}
