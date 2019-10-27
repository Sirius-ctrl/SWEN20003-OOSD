import java.util.Random;

import org.newdawn.slick.Input;

/**
 * This class define and implement the behavior of boss including
 * how boss is moving and shooting.
 */
public class Boss extends Enemy {
	/** The boss graph path */
	public static final String PATH= "res/boss.png";
	
	private static final int SCORE = 5000;
	private static final float ENTERING_SPEED = 0.05f;
	private static final float INI_Y = 72;
	private static final int MIN_X_RANGE = 128;
	private static final int MAX_X_RANGE = 896;
	private static final float MODE1_SPEED = 0.2f;
	private static final float MODE2_SPEED = 0.1f;
	private static final int LIFE = 60;
	private static final int SHOOTING_OFFSET1 = 97;
	private static final int SHOOTING_OFFSET2 = 74;
	private static final int SHOOTING_INTERVAL = 200;
	private static final int SHOOTING_PERIOD = 3000;
	private static final int FIRST_DELAY = 5000;
	private static final int SECOND_DELAY = 2000;
	
	private final int ENTER_DELAY;
	
	private float xMoveTo = 0;
	private int timer = 0;
	private int mode = 1;
	private int timeFromLastShoot = SHOOTING_INTERVAL;
	private int timePastFromMovingStarted = 0;
	
	private Random random = new Random();

	/**
	 * Constructor
	 * @param x x coordinate of the starting point
	 * @param y y coordinate of the starting point
	 * @param delay the time in millisecond should past before it starting to moves into the screen
	 */
	public Boss(float x, float y, int delay) {
		super(PATH, x, y, SCORE, LIFE, delay);
		ENTER_DELAY = delay;
	}
	
	
	/* (non-Javadoc)
	 * @see Enemy#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
		
		// move into the screen when reach the delay time
		// two different mode represent two different repeating action of boss
		if(World.getInstance().getTimer() >= ENTER_DELAY && getY() < INI_Y) {
			move(0, delta*ENTERING_SPEED);
			
		}else if(getY() >= INI_Y){
			if(mode == 1 && timer >= FIRST_DELAY) {
				if(xMoveTo == 0) {
					xMoveTo = MIN_X_RANGE + random.nextInt(MAX_X_RANGE);
				}
				
				mode1(delta);
			}else if(mode== 2 && timer >= SECOND_DELAY) {
				if(xMoveTo == 0) {
					xMoveTo = MIN_X_RANGE + random.nextInt(MAX_X_RANGE);
				}
				
				mode2(delta);
			}else {
				timer += delta;
			}
		}
	}
	

	/*
	 * move and stay, representing step 1-3
	 */
	private void mode1(int delta) {
		if( Math.abs(getX() - xMoveTo) < delta*MODE1_SPEED ) {
			xMoveTo = 0;
			timer = 0;
			mode = 2;
		}else {
			if(getX() > xMoveTo) {
				move(-delta*MODE1_SPEED, 0);
			}else {
				move(delta*MODE1_SPEED, 0);
			}
		}
	}
	

	/*
	 *	move and keep shooting, representing step 4-7
	 */
	private void mode2(int delta) {
		if( Math.abs(getX() - xMoveTo) < delta*MODE2_SPEED && timePastFromMovingStarted >= SHOOTING_PERIOD) {
			xMoveTo = 0;
			timer = 0;
			mode = 1;
			timePastFromMovingStarted = 0;
			timeFromLastShoot = SHOOTING_INTERVAL;
		}else {
			if(getX() > xMoveTo) {
				move(-delta*MODE2_SPEED, 0);
			}else if(getX() < xMoveTo){
				move(delta*MODE2_SPEED, 0);
			}
			
			if (timePastFromMovingStarted < SHOOTING_PERIOD && timeFromLastShoot >= SHOOTING_INTERVAL){
				shoot();
				timeFromLastShoot = 0;
			}else {
				timeFromLastShoot += delta;
			}
			
			timePastFromMovingStarted += delta;
		}
	}
	
	
	/*
	 * shoot at proper place
	 */
	private void shoot() {
		World.getInstance().addSprite(new EnemyLaser(getX()-SHOOTING_OFFSET1, getY()));
		World.getInstance().addSprite(new EnemyLaser(getX()-SHOOTING_OFFSET2, getY()));
		World.getInstance().addSprite(new EnemyLaser(getX()+SHOOTING_OFFSET1, getY()));
		World.getInstance().addSprite(new EnemyLaser(getX()+SHOOTING_OFFSET2, getY()));
	}

}
