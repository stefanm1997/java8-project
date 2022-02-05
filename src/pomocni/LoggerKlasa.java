package pomocni;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggerKlasa {

	private static Logger logger = Logger.getLogger("errorLogger");
	private static FileHandler fileHandler;
	private static boolean prviPut = true;

	public synchronized static Logger getLogger() {
		if (prviPut) {
			try {
				fileHandler = new FileHandler("error.log", true);
				fileHandler.setFormatter(new SimpleFormatter());
				logger.addHandler(fileHandler);
				prviPut = false;
				logger.setUseParentHandlers(false);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return logger;
	}

}
