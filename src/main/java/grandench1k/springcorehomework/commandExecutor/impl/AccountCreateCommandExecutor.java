package grandench1k.springcorehomework.commandExecutor.impl;

import grandench1k.springcorehomework.commandExecutor.CommandExecutor;
import grandench1k.springcorehomework.constant.ConsoleOperationType;
import grandench1k.springcorehomework.model.account.Account;
import grandench1k.springcorehomework.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountCreateCommandExecutor implements CommandExecutor {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountCreateCommandExecutor(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter the user id for which to create an account:");
        int userId = Integer.parseInt(scanner.nextLine());
        try {
            Account account = accountService.createAccount(userId);
            System.out.printf("New account created with ID: %d for user: %d ", account.getId(), account.getUserId());
        } catch (RuntimeException e) {
            System.out.printf(e.getMessage());
        }
    }

    @Override
    public String getOperationType() {
        return ConsoleOperationType.ACCOUNT_CREATE.toString();
    }
}
