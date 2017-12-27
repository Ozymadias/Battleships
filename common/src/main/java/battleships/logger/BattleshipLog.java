package battleships.logger;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class BattleshipLog {

  private final Logger logger;

  private BattleshipLog(Logger logger) {
    this.logger = logger;
  }

  public static BattleshipLog provideLogger(Class<?> classToLog) {
    return new BattleshipLog(LogManager.getLogger(classToLog));
  }

  public void debug(Object message, Throwable throwable) {
    logger.debug(message, throwable);
  }

  public void debug(Object message) {
    logger.debug(message);
  }

  public void error(Object object) {
    logger.error(object);
  }

  public void error(String message, Throwable t) {
    logger.error(message, t);
  }

  public void info(Object message) {
    logger.info(message);
  }

  public void info(Object message, Throwable throwable) {
    logger.info(message, throwable);
  }

  public void fatal(Object message, Throwable throwable) {
    logger.fatal(message, throwable);
  }

  public void fatal(Object message) {
    logger.fatal(message);
  }
}
