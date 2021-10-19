package message.logger;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class CustomLoggerFileFormatter extends Formatter{
    @NotNull
    private String tag;

    @Inject
    public CustomLoggerFileFormatter(@NotNull String tag){
        setTag(tag);
    }

    @Override
    public String format(LogRecord rec) {
        StringBuffer buf = new StringBuffer(1000);
        buf.append("<" + tag + "> ");
        buf.append(rec.getMessage());
        buf.append("</" + tag + ">\n");
        return buf.toString();
    }

    public void setTag(@NotNull String tag) {
        this.tag = tag;
    }
}
