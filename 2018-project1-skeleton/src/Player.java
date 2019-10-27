import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Define and implement the action of the player including the status 
 * of player picked power up, player laser shoot, and the contact between
 * player and enemy stuff.
 */
public class Player extends Sprite {
	/** the player graph path */
	public final static String PLAYER_SPRITE_PATH = "res/spaceship.png";
	/** the player life graph path */
	public final static String LIVE_PATH = "res/lives.png";
	/** X coordinate of player's starting point */
	public final static int PLAYER_INITIAL_X = 512;
	/** Y coordinate of player's starting point */
	public final static int PLAYER_INITIAL_Y = 688;
	/** X coordinate of player's life starting point */
	public final static int LIVE_X = 20;
	/** Y coordinate of player's life starting point */
	public final static int LIVE_Y = 696;
	/** interval between each life graph */
	public final static int LIVE_INTERVAL = 40;
	
	private final static float SPEED = 0.5f;
	private final static int GET_HIT_SHIELD = 3000;
	private final static int BOOST_DURATION = 5000;
	private final static int NORMAL_SHOOTING_INTERVAL = 350;
	private final static int BOOST_SHOOTING_INTERVAL = 150;
	
	private int life = 3;
	private int shootInterval = NORMAL_SHOOTING_INTERVAL;
	private int timeFromLastShoot = 0;
	private int shieldTimer = 0;
	private int speedUpTimer = 0;


	private ShieldAroundPlayer shield = null;
	
	
	/**
	 * Constructor
	 */
	public Player() {
		super(PLAYER_SPRITE_PATH, PLAYER_INITIAL_X, PLAYER_INITIAL_Y);
	}
	
	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
		doMovement(input, delta);
		
		// update the shield status
		if(shieldTimer > 0) {
			shieldTimer -= delta;
			drawShield();
		}else {
			shieldTimer = 0;
			if(shield != null) {
				removeShield();
			}
		}
		
		// update the speed up boost
		if(speedUpTimer > 0) {
			speedUpTimer -= delta;
			shootInterval = BOOST_SHOOTING_INTERVAL;
		}else {
			speedUpTimer = 0;
			shootInterval = NORMAL_SHOOTING_INTERVAL;
		}
		
		// do shooting
		if (input.isKeyDown(Input.KEY_SPACE) && timeFromLastShoot >= shootInterval) {
			World.getInstance().addSprite(new PlayerLaser(getX(), getY()));
			//reset the timer
			timeFromLastShoot = 0;
		}else {
			timeFromLastShoot += delta;
		}
	}
	
	
	/* (non-Javadoc)
	 * @see Sprite#contactSprite(Sprite)
	 */
	public void contactSprite(Sprite other) {
		//check if make contact with anything 
		
		// if contact with enemy, life - 1
		// shield up for 3 seconds
		if (other instanceof Enemy || other instanceof EnemyLaser) {
			if(shieldTimer <= 0) {
				if(life > 1) {
					life -= 1;
					shieldTimer = GET_HIT_SHIELD;
				}else {
					System.exit(0);
				}
			}
			
			//remove the laser from the screen
			if (other instanceof EnemyLaser) {
				other.deactivate();
			}
			
		}else if(other instanceof ShieldPowerUp) {
			shieldTimer = BOOST_DURATION;
			other.deactivate();
		}else if(other instanceof ShootSpeedUp) {
			speedUpTimer = BOOST_DURATION;
			other.deactivate();
		}
	}
	
	
	/*
	 * draw a shield around the player if the shield is activated
	 */
	private void drawShield() {
		if(shield == null) {
			shield = new ShieldAroundPlayer(getX(), getY());
			World.getInstance().addSprite(shield);
		}else {
			shield.moveTo(getX(), getY());
		}
	}
	
	
	private void removeShield() {
		shield.deactivate();
		shield = null;
	}
	
	
	private void displayLive() throws SlickException {	
		Image image = new Image(LIVE_PATH);
		
		for(int i=0;i<life;i++) {
			image.draw(LIVE_X+i*LIVE_INTERVAL, LIVE_Y);
		}	
	}


	
	private void doMovement(Input input, int delta) {
		// handle horizontal movement
		float dx = 0;
		if (input.isKeyDown(Input.KEY_LEFT)) {
			dx -= SPEED;
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			dx += SPEED;
		}

		// handle vertical movement
		float dy = 0;
		if (input.isKeyDown(Input.KEY_UP)) {
			dy -= SPEED;
		}
		if (input.isKeyDown(Input.KEY_DOWN)) {
			dy += SPEED;
		}
		
		move(dx * delta, dy * delta);
		clampToScreen();
	}
	
	
	public void render() {
		super.render();
		
		try {
			displayLive();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
