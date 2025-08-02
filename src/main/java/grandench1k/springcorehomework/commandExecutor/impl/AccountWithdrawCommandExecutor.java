package grandench1k.springcorehomework.commandExecutor.impl;

import grandench1k.springcorehomework.commandExecutor.CommandExecutor;
import grandench1k.springcorehomework.constant.ConsoleOperationType;
import grandench1k.springcorehomework.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountWithdrawCommandExecutor implements CommandExecutor {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountWithdrawCommandExecutor(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter account ID to withdraw from:");
            int accountId = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter amount to withdraw:");
            int amountOfMoney = Integer.parseInt(scanner.nextLine());
            System.out.printf(
                    "Amount %s withdrew from account ID: %s",
                    amountOfMoney,
                    accountService.withdrawAccountMoneyAmount(accountId, amountOfMoney)
            );
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public String getOperationType() {
        return ConsoleOperationType.ACCOUNT_WITHDRAW.toString();
    }
}
