import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * This class mean to build the world of the game, and sprites are added at
 * here. Rendering and Update of sprite and background also happened at this
 * place.
 */
public class World {
	/** background path */
	public static final String BACKGROUND_PATH = "res/space.png";
	/** a .txt file that contains waves information */
	public static final String FILE_PATH = "res/waves.txt";
	/** the x coordinate of score string */
	public final static int SCORE_X = 20;
	/** the y coordinate of score string */
	public final static int SCORE_Y = 738;
	
	private final static int SPEED_UP_THE_GAME = 5;
	private static final float BACKGROUND_SCROLL_SPEED = 0.2f;
	
	private float backgroundOffset = 0;
	private Image background;
	
	// record the time past since the game start
	private int timer = 0;
	private int score = 0;
	
	private static World world;
	
	/**
	 * Return the current using world itself, for the purpose of 
	 * using the method define in world. Most of the time, it used for
	 * adding new sprite.
	 * @return the current using world instance itself
	 */
	public static World getInstance() {
		if (world == null) {
			world = new World();
		}
		return world;
	}
	
	private ArrayList<Sprite> sprites = new ArrayList<>();
	
	/**
	 * Add a new sprite to the game world.
	 * @param sprite a sprite object that need to be added into the game
	 */
	public void addSprite(Sprite sprite) {
		sprites.add(sprite);
	}
	
	
	/**
	 * Constructor
	 */
	public World() {
		
		sprites.add(new Player());
		
		try {
			background = new Image(BACKGROUND_PATH);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		world = this;
		
		// Load enemies from the file
		Loader.load(FILE_PATH);
		
	}
	
	
	/**
	 * update all the sprite in world for a frame. The game started time
	 * should also be incremented by delta.
	 * @param input keyboard input
	 * @param delta	time in millisecond has past since last update
	 */
	public void update(Input input, int delta) {
		
		if(input.isKeyDown(Input.KEY_S)) {
			delta *= SPEED_UP_THE_GAME;
		}
		
		// Update how the time past in the game
		timer += delta;
		
		// Update all sprites
		for (int i = 0; i < sprites.size(); ++i) {
			sprites.get(i).update(input, delta);
		}
		
		// Handle collisions
		for (int i = 0; i < sprites.size(); ++i) {
			for (int j = 0; j < sprites.size(); ++j) {
				if(sprites.get(i) != sprites.get(j) && sprites.get(i).getBoundingBox().intersects(sprites.get(j).getBoundingBox())) {
					sprites.get(i).contactSprite(sprites.get(j));
				}
			}
		}
		
		// Clean up inactive sprites
		for (int i = 0; i < sprites.size(); ++i) {
			if (sprites.get(i).getActive() == false) {
				sprites.remove(i);
				// decrement counter to make sure we don't miss any
				--i;
			}
		}
		
		backgroundOffset += BACKGROUND_SCROLL_SPEED * delta;
		backgroundOffset = backgroundOffset % background.getHeight();
	}
	
	
	/**
	 * Render the entire screen, so it reflects the current game state.
	 * @param g The Slick graphics object, used for drawing graph.
	 */
	public void render(Graphics g) throws SlickException {
		// Tile the background image
		for (int i = 0; i < App.SCREEN_WIDTH; i += background.getWidth()) {
			for (int j = -background.getHeight() + (int)backgroundOffset; j < App.SCREEN_HEIGHT; j += background.getHeight()) {
				background.draw(i, j);
			}
		}
		
		// Draw all sprites
		for (Sprite sprite : sprites) {
			// when rendering player, draw a extra score
			sprite.render();
		}
		
		// draw the score text
		displayScore(g);
	}

	
	/**
	 * Return how much time has past in milliseconds.
	 * @return timer Time has past in this game in milliseconds.
	 */
	public int getTimer() {
		return timer;
	}
	
	
	/**
	 * Add certain amount of score to score.
	 * @param score total score the player has gained so far.
	 */
	public void addScore(int score) {
		this.score += score;
	}
	
	
	/**
	 * Draw the score String on screen
	 * @param g	The Slick graphics object, used for drawing score.
	 */
	public void displayScore(Graphics g) {
		g.drawString("Score: "+score, SCORE_X, SCORE_Y);
	}
}
