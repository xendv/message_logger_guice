package message.logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Main {
    public static void main(@NotNull String[] args) {

        String errMsg = "Launch with parameters: '-c' key for enabling console logging and(or) " +
                "'-f' and one word tag for file logging";

        //checking arguments
        if (args.length < 1) {
            System.out.println(errMsg);
        } else {
            HashMap<String, String> keys = new HashMap<>();
                        /*(Map.of(
                    "key1","-c",
                    "key2","-f",
                    "tag","tag-tag"
                    ));*/
            for (int i = 0; i < args.length; i++) {
                keys.put(String.valueOf(i), args[i]);
            }
            var valuesCopy = ((HashMap<String,String>)keys.clone()).values();
            if (!(valuesCopy.contains("-c") || valuesCopy.contains("-f"))) System.out.println(errMsg);
            else {
                if (keys.containsValue("-c")) {
                    valuesCopy.remove("-c");
                }
                if (keys.containsValue("-f")) {
                    valuesCopy.remove("-f");
                    if (valuesCopy.size() == 0){
                        System.out.println(errMsg + "\n//// No tag found!");
                        return;
                    }
                    else {
                        String tag = (String) valuesCopy.toArray()[0];
                        keys.put("tag", tag);
                    }
                }

                //creating injector and launching Application
                final Injector injector = Guice.createInjector(new MessageLoggerModule(keys));
                injector.getInstance(Application.class).waitForInput(injector.getInstance(MessageProcessor.class));
            }
        }
    }
}
