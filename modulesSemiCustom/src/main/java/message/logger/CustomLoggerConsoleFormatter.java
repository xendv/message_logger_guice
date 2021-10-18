package message.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomLoggerConsoleFormatter extends Formatter{

    @Override
    public String format(LogRecord rec) {
        StringBuffer buf = new StringBuffer(1000);
        buf.append(rec.getMessage() + "\n");
        return buf.toString();
    }
}
