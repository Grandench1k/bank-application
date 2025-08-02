package grandench1k.springcorehomework.commandExecutor.impl;

import grandench1k.springcorehomework.commandExecutor.CommandExecutor;
import grandench1k.springcorehomework.constant.ConsoleOperationType;
import grandench1k.springcorehomework.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountDepositCommandExecutor implements CommandExecutor {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountDepositCommandExecutor(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter account ID to deposit:");
            int accountId = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter amount to deposit:");
            int amountOfMoney = Integer.parseInt(scanner.nextLine());
            System.out.printf(
                    "Amount %s deposited to account ID: %s".formatted(
                            accountService.depositAccountMoneyAmount(accountId, amountOfMoney),
                            accountId
                    )
            );
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getOperationType() {
        return ConsoleOperationType.ACCOUNT_DEPOSIT.toString();
    }
}
