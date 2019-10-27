import org.newdawn.slick.Input;
import java.util.Random;

/**
 * The superclass of the any power up in this game world
 * it handled he collision between object and drop of powerup
 * when an enemy has been destroyed
 */
abstract class Enemy extends Sprite {
	
	private static final int DROP_RATE = 5;
	
	private final int SCORE;
	private final int DELAY;
	
	private Random random = new Random();
	
	private int life;

		
	public Enemy(String imagePath, float x, float y, int score, int life, int delay) {
		super(imagePath, x, y);
		
		this.SCORE = score;
		this.life = life;
		DELAY = delay;
	}

	
	/* (non-Javadoc)
	 * @see Sprite#contactSprite(Sprite)
	 */
	@Override
	public void contactSprite(Sprite other) {
		// Check if the enemy made contact with the player laser
		// if so, deactivate the enemy, and add the relative score
		// to the player
		
		if(other instanceof PlayerLaser) {
			// the contact only validated when enemies moved into screen already
			if(other instanceof PlayerLaser && World.getInstance().getTimer() >= DELAY) {
				other.deactivate();
				
				if(life <= 1) {
					deactivate();
					dropPowerUp();
					World.getInstance().addScore(SCORE);
				}else {
					life -= 1;
				}
			}
		}
	}
	
	
	/**
	 * determine if this enemy should drop a power up or not
	 * by the drop rate,  based on uniformed probability distribution
	 */
	public void dropPowerUp() {
		if (random.nextInt(100/DROP_RATE) == 1) {
			World.getInstance().addSprite(new ShieldPowerUp(getX(), getY()));
		}
		
		if (random.nextInt(100/DROP_RATE) == 1) {
			World.getInstance().addSprite(new ShootSpeedUp(getX(), getY()));
		}
	}
	
	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public abstract void update(Input input, int delta);
}
