package battleships.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * This is a wrapper class for log4j logger.
 */
public class BattleshipLog {

  private final Logger logger;

  private BattleshipLog(Logger logger) {
    this.logger = logger;
  }

  /**
   * Provides BattleshipLog logger for specific class.
   *
   * @param classToLog class name where instance of logger is created.
   */
  public static BattleshipLog provideLogger(Class<?> classToLog) {
    return new BattleshipLog(LogManager.getLogger(classToLog));
  }

  /**
   * For logging error provided in given message.
   */
  public void error(Object message) {
    logger.error(message);
  }

  /**
   * For logging info provided in given message.
   */
  public void info(Object message) {
    logger.info(message);
  }

  /**
   * For logging info provided in given message and Throwable object.
   */
  public void info(Object message, Throwable throwable) {
    logger.info(message, throwable);
  }

}
