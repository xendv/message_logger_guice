package message.logger;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Application {
    MessageProcessor messageProcessor;
    public void waitForInput(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Waiting for new lines. Key in Ctrl+D to exit.");
            while (true) {
                String input = scanner.nextLine();
                messageProcessor.writeLog(input);
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            //e.printStackTrace();
        }
    }

    //@LoggerTracker
    public String getInput(Scanner scanner){
        String input = scanner.nextLine();

        return input;
    }
}
