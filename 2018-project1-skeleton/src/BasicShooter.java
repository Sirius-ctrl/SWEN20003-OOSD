import java.util.Random;

import org.newdawn.slick.Input;

/**
 * This class define and implement the behavior of Basic shooter including
 * how it is moving and shooting.
 */
public class BasicShooter extends Enemy {
	/** the basic shooter graph path */
	public static final String PATH = "res/basic-shooter.png";
	
	private static final float SPEED = 0.2f;
	private static final int SHOOTING_INTERVAL = 3500;
	private static final int SCORE = 200;
	private static final int MIN_RANGE = 48;
	private static final int MAX_RANGE = 464;
	private static final int LIFE = 1;
	
	
	private Random random = new Random();
	private final float RANDOM_Y = MIN_RANGE + random.nextInt(MAX_RANGE);
	private int timeFromLastShoot = 0;
	
	private final int DELAY;

	/**
	 * Constructor
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 * @param delay the time in millisecond should past before it starting to moves into the screen
	 */
	public BasicShooter(float x, float y, int delay) {
		super(PATH, x, y, SCORE, LIFE, delay);
		this.DELAY = delay;
	}
	

	/* (non-Javadoc)
	 * @see Enemy#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
		// move into the screen once reach the delay time and stop at randomly picked Y
		if(World.getInstance().getTimer() >= DELAY && this.getY() < RANDOM_Y) {
			move(0, SPEED*delta);
		}else {
			// do shooting
			if(timeFromLastShoot >= SHOOTING_INTERVAL) {
				World.getInstance().addSprite(new EnemyLaser(getX(), getY()));
				timeFromLastShoot = 0;
			}else {
				timeFromLastShoot += delta;
			}
			
		}
	}

}
