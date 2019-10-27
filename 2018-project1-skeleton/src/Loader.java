import java.io.FileReader;
import java.io.BufferedReader;

/**
 * This class only contains one static method which is built to 
 * load the enemies wave info for a .txt files
 */
public class Loader {
	/** starting Y coordinate of the enemy */
	private static final int STARTING_POINT = -64;
	
	/**
	 * load the enemies wave from target file path
	 * @param path the .txt file path that contain enemies wave info
	 */
	public static void load(String path) {
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String text;
			
			while((text = br.readLine()) != null) {
				String[] parts = text.split(",");
				
				if(text.contains("#")) {
					continue;
				}else if(parts[0].equals("BasicEnemy")) {
					World.getInstance().addSprite(new BasicEnemy(Float.parseFloat(parts[1]), STARTING_POINT, Integer.parseInt(parts[2])));
				}else if(parts[0].equals("SineEnemy")) {
					World.getInstance().addSprite(new SineEnemy(Float.parseFloat(parts[1]), STARTING_POINT, Integer.parseInt(parts[2])));
				}else if(parts[0].equals("BasicShooter")) {
					World.getInstance().addSprite(new BasicShooter(Float.parseFloat(parts[1]), STARTING_POINT, Integer.parseInt(parts[2])));
				}else if(parts[0].equals("Boss")) {
					World.getInstance().addSprite(new Boss(Float.parseFloat(parts[1]), STARTING_POINT, Integer.parseInt(parts[2])));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
