package message.logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Handler;
import java.util.logging.Logger;

@Singleton
public class MessageProcessor implements Processor {

    private static int counter = 1;

    @Inject
    @NotNull
    private Logger logger;

    @Inject(optional = true)
    @ConsoleWriter
    public Handler consoleLogger;

    @Inject(optional = true)
    @FileWriter
    public Handler fileLogger;

    @Inject
    public void setup(){
        // Note: logger may have ConsoleHandler as default,
        // so we reset handlers
        logger.setUseParentHandlers(false);
    }

    @Override
    public void writeLog(String input){
        if(consoleLogger != null) {
            logger.addHandler(consoleLogger);
            logger.info(counter++ + ". " + input);
            logger.removeHandler(consoleLogger);
        }
        if(fileLogger != null) {
            logger.addHandler(fileLogger);
            logger.info(counter++ + ". " + input);
            logger.removeHandler(fileLogger);
        }
    }
}
