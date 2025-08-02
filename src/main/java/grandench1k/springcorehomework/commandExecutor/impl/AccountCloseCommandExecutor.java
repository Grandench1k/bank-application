package grandench1k.springcorehomework.commandExecutor.impl;

import grandench1k.springcorehomework.commandExecutor.CommandExecutor;
import grandench1k.springcorehomework.constant.ConsoleOperationType;
import grandench1k.springcorehomework.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountCloseCommandExecutor implements CommandExecutor {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountCloseCommandExecutor(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter account ID to close:");
            int accountId = Integer.parseInt(scanner.nextLine());
            System.out.printf(
                    "Account with ID: %s has been closed",
                    accountService.closeAccount(accountId)
            );
        } catch (RuntimeException e) {
            System.out.printf(e.getMessage());
        }
    }

    @Override
    public String getOperationType() {
        return ConsoleOperationType.ACCOUNT_CLOSE.toString();
    }
}
