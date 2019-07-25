import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	
	public GameEngine InitialiseGameState() throws IOException{
		
		GameEngine engine = null;
		
		// TODO Read input from a .properties file located within resource directory.
		
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath!");
			}
			
			int numPlayers = Integer.valueOf(prop.getProperty("numPlayers"));
			int maxHandSize = Integer.valueOf(prop.getProperty("maxHandSize"));
			int zeroValue = Integer.valueOf(prop.getProperty("zeroBidValue"));
			
			engine = new GameEngine(numPlayers, maxHandSize, zeroValue);
			
		} catch (Exception e) {
			System.err.println("Exception: + e");
			System.err.println("The Application will now close.");
			CrystalBrook.ApplicationExit();
		}
		
		return engine;
	}

}
