package message.logger;

import com.google.inject.ImplementedBy;

@FunctionalInterface
@ImplementedBy(MessageProcessor.class)
public interface Processor {
    void writeLog(String input);
}