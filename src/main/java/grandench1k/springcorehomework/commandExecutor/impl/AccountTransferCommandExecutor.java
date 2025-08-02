package grandench1k.springcorehomework.commandExecutor.impl;

import grandench1k.springcorehomework.commandExecutor.CommandExecutor;
import grandench1k.springcorehomework.constant.ConsoleOperationType;
import grandench1k.springcorehomework.service.AccountService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AccountTransferCommandExecutor implements CommandExecutor {
    private final AccountService accountService;
    private final Scanner scanner;

    public AccountTransferCommandExecutor(AccountService accountService, Scanner scanner) {
        this.accountService = accountService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Enter source account ID:");
            int accountIdFrom = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter target account ID:");
            int accountIdTo = Integer.parseInt(scanner.nextLine());
            System.out.println("Enter amount to transfer:");
            int amountOfMoney = Integer.parseInt(scanner.nextLine());
            System.out.printf(accountService.transferMoneyAmount(accountIdFrom, accountIdTo, amountOfMoney));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getOperationType() {
        return ConsoleOperationType.ACCOUNT_TRANSFER.toString();
    }
}
