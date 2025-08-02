package grandench1k.springcorehomework.listener;

import grandench1k.springcorehomework.commandExecutor.CommandExecutor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class OperationsConsoleListener implements Runnable {

    private final Map<String, CommandExecutor> commandExecutorMap;
    private final Scanner scanner;

    public OperationsConsoleListener(List<CommandExecutor> commandExecutors, Scanner scanner) {
        this.scanner = scanner;
        commandExecutorMap = new HashMap<>();
        commandExecutors
                .forEach(commandExecutor ->
                        {
                            commandExecutorMap.put(commandExecutor.getOperationType(), commandExecutor);
                        }
                );
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(
                    String.join("\n",
                            "\nPlease enter one of operation type:",
                            " -ACCOUNT_CREATE",
                            " -SHOW_ALL_USERS",
                            " -ACCOUNT_CLOSE",
                            " -ACCOUNT_WITHDRAW",
                            " -ACCOUNT_DEPOSIT",
                            " -ACCOUNT_TRANSFER",
                            " -USER_CREATE"
                    )
            );
            String input = scanner.nextLine();
            if (commandExecutorMap.containsKey(input)) {
                commandExecutorMap.get(input).execute();
            } else {
                System.out.print("Invalid operation");
            }
        }
    }

    public void start() {
        System.out.println("Console listener started");
    }

    public void stop() {
        System.out.println("Console listener stopped");
    }
}
