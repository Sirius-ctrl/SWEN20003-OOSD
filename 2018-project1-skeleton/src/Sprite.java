import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

/**
 * This abstract class restrict the minimal content should be included
 * in each subclass, all object in the game world except background extends
 * this class
 */
public 	abstract class Sprite {
	
	private Image image = null;
	private float x;
	private float y;
	private BoundingBox bb;
	private boolean active = true;
	
	
	/**
	 * Constructor
	 * @param imageSrc image path
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 */
	public Sprite(String imageSrc, float x, float y) {
		try {
			image = new Image(imageSrc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		
		bb = new BoundingBox(image, x, y);
	}
	
	/**
	 * Returns true whenever the sprite is on screen. Otherwise, false.
	 */
	public boolean onScreen() {
		return x >= 0 && x <= App.SCREEN_WIDTH
			&& y >= 0 && y <= App.SCREEN_HEIGHT;
	}
	
	
	/**
	 *  Forces the sprite to remain on the screen
	 */
	public void clampToScreen() {
		x = Math.max(x, 0);
		x = Math.min(x, App.SCREEN_WIDTH);
		y = Math.max(y, 0);
		y = Math.min(y, App.SCREEN_HEIGHT);
	}
	
	
	/**
	 * a abstract class, any subclass of sprite will implement a concrete 
	 * update method to update the content for this frame.
	 * @param input	keyboard input
	 * @param delta time in millisecond has past since last update
	 */
	public abstract void update(Input input, int delta);
	
	/**
	 * render the sprite for this frame in world
	 */
	public void render() {
		image.drawCentered(x, y);
	}
	
	/**
	 * Called whenever this Sprite makes contact with another.
	 */
	public void contactSprite(Sprite other) {
		
	}

	public float getX() { return x; }
	public float getY() { return y; }
	public boolean getActive() { return active; }
	public void deactivate() { active = false; }
	
	/**
	 * Create a BoundingBox for this sprite
	 * @return BoundingBox A invisible box around sprite to determine if two sprite make contact
	 */
	public BoundingBox getBoundingBox() {
		return new BoundingBox(bb);
	}
	
	
	/**
	 * Move sprite to x+dx and y+dy
	 * @param dx x coordinate changes
	 * @param dy y coordinate changes
	 */
	public void move(float dx, float dy) {
		x += dx;
		y += dy;
		bb.setX(x);
		bb.setY(y);
	}
	
	
	/**
	 * Move sprite to x and y 
	 * @param x destination x coordinate
	 * @param y	destination y coordinate
	 */
	public void moveTo(float x, float y) {
		this.x = x;
		this.y = y;
		bb.setX(x);
		bb.setY(y);
	}
}
