import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	public GameEngine InitialiseGameState() throws IOException{
		
		GameEngine engine = null;
		
		// TODO Read input from a .properties file located within resource directory.
		
		try {
			System.out.println("Attempting to read config file...");
			
			Properties prop = new Properties();
			String propFileName = "config.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			if (inputStream != null) {
				System.out.println("Found config file!");
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath!");
			}
			
			System.out.println("Parsing values...");
			int numPlayers = Integer.valueOf(prop.getProperty("numPlayers"));
			int maxHandSize = Integer.valueOf(prop.getProperty("maxHandSize"));
			int zeroValue = Integer.valueOf(prop.getProperty("zeroBidValue"));
			
			System.out.println("Initialising Game Engine...");
			engine = new GameEngine(numPlayers, maxHandSize, zeroValue);
			
			System.out.println("Game Engine initialised with " + numPlayers +
					" players, for a game with a max hand size of " + maxHandSize
					+ ", and a value for zero bids of " + zeroValue + " points.");
			
		} catch (Exception e) {
			System.err.println("Exception:" + e);
			System.err.println("The Application will now close.");
			CrystalBrook.ApplicationExit();
		}
		
		return engine;
	}

}
