import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class SimpleSlickGame extends BasicGame {
	public static int TEXT_OFFSET = 100;
	public static int SCREEN_WIDTH = 1024;
	public static int SCREEN_HEIGHT = 768;
	private int count = 0;

	private Image background;

	public SimpleSlickGame() {
		super("SimpleSlickGame");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		background = new Image("assets/background.jpg");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_UP)) {
			count++;
		}

		if (input.isKeyDown(Input.KEY_DOWN)) {
			count--;
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		background.draw(0, 0);
		g.drawString("Count = " + count, TEXT_OFFSET, TEXT_OFFSET);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new SimpleSlickGame());
			app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}