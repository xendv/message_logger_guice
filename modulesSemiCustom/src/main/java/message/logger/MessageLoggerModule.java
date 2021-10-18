package message.logger;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

public class MessageLoggerModule extends AbstractModule {
    HashMap<String,String> keys;
    String filename="./MessageLogger_log.txt";
    public MessageLoggerModule(HashMap<String, String> keys){
        this.keys=keys;
    }

    @Override
    protected void configure(){

        // if we need to use console
        if (keys.containsValue("-c")){
            bind(Handler.class).annotatedWith(ConsoleWriter.class).toInstance(consoleHandler(new CustomLoggerConsoleFormatter()));
        }
        // if we need to use file
        if (keys.containsValue("-f")){
            try {
                bind(Handler.class).annotatedWith(FileWriter.class).toInstance(fileHandler(customLoggerFileFormatter(keys.get("tag")),filename));
            }
            catch (Exception e) {e.printStackTrace();}
        }
    }

    @NotNull
    @Provides
    CustomLoggerFileFormatter customLoggerFileFormatter(@NotNull String tag) {
        return new CustomLoggerFileFormatter(tag);
    }

    @NotNull
    @Provides
    FileHandler fileHandler(@NotNull CustomLoggerFileFormatter customLoggerFormatter, @NotNull String fileName) throws IOException {
        FileHandler fileHandler = new FileHandler(fileName);
        fileHandler.setFormatter(customLoggerFormatter);
        return fileHandler;
    }

    @NotNull
    @Provides
    ConsoleHandler consoleHandler(@NotNull CustomLoggerConsoleFormatter customLoggerConsoleFormatter) {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(customLoggerConsoleFormatter);
        return consoleHandler;
    }
}
