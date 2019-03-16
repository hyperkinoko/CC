package section_8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

public class Kadai_8 {
	public static void main(String[] args) {
		Level logLevel;
		try {
			String input = "INFO";
			if (args != null && args.length == 1) {
				input = args[0].toUpperCase();
			}
//			System.out.println(input);
			logLevel = Level.valueOf(Level.class, input);
		} catch (IllegalArgumentException e) {
			logLevel = Level.INFO;
		}
		System.setProperty("org.slf4j.simpleLogger.log.Kadai_8", logLevel.toString());
		Logger logger = LoggerFactory.getLogger("Kadai_8");

		logger.trace("トレースレベルログ");
		logger.debug("デバッグレベルログ");
		logger.info("情報レベルログ");
		logger.warn("ワーニングレベルログ");
		logger.error("エラーレベルログ");
	}

}
