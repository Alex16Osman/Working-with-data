import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Main {

    private static final Logger loggerQueries = LogManager.getRootLogger();
    private static Logger loggerErr = LogManager.getLogger(Main.class);
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        try {
            while (true) {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);

                switch (tokens[0]) {
                    case "add":
                        loggerQueries.info("Add: " + tokens[1]);
                        executor.addCustomer(tokens[1]);
                        break;
                    case "list":
                        loggerQueries.info("list");
                        executor.listCustomers();
                        break;
                    case "remove":
                        loggerQueries.info("remove: " + tokens[1]);
                        executor.removeCustomer(tokens[1]);
                        break;
                    case "count":
                        loggerQueries.info("count: " + "There are " + executor.getCount() + " customers");
                        System.out.println("There are " + executor.getCount() + " customers");
                        break;
                    case "help":
                        loggerQueries.info("help: " + helpText);
                        System.out.println(helpText);
                        break;
                    default:
                        loggerErr.error(COMMAND_ERROR);
                        System.out.println(COMMAND_ERROR);
                        break;
                }
            }

        } catch (IllegalArgumentException | InvalidEmailFormatException e) {
            loggerErr.error(e.getMessage());
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            loggerErr.error(e.getMessage());
            System.out.println(COMMAND_ERROR);
        }
    }
}
