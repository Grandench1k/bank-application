package grandench1k.springcorehomework.service;

import grandench1k.springcorehomework.configuration.ApplicationConfiguration;
import grandench1k.springcorehomework.model.account.Account;
import grandench1k.springcorehomework.model.account.AccountProperties;
import grandench1k.springcorehomework.model.user.User;
import grandench1k.springcorehomework.repo.AccountRepo;
import grandench1k.springcorehomework.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AccountService {
    private final UserRepo userRepo;
    private final AccountRepo accountRepo;
    private final AccountProperties accountProperties;

    public AccountService(UserRepo userRepo, AccountRepo accountRepo, AccountProperties accountProperties) {
        this.userRepo = userRepo;
        this.accountRepo = accountRepo;
        this.accountProperties = accountProperties;
    }

    public Account saveAccount(int userId) {
        Account account = new Account();
        account.setUserId(userId);
        account.setMoneyAmount(accountProperties.getDefaultMoneyAmount());
        return accountRepo.save(account);
    }

    public Account createAccount(int userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new RuntimeException("User with this ID: %s not found".formatted(userId))
        );
        Account savedAccount = saveAccount(userId);
        ArrayList<Account> newAccountList = user.getAccountList();
        newAccountList.add(savedAccount);
        user.setAccountList(newAccountList);
        userRepo.save(user);
        return savedAccount;
    }

    public Account depositAccountMoneyAmount(int accountId, int moneyAmount) {
        Account account = accountRepo.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account with this ID: %s not found".formatted(accountId))
        );
        account.setMoneyAmount(account.getMoneyAmount() + moneyAmount);
        return accountRepo.save(account);
    }

    public int withdrawAccountMoneyAmount(int accountId, int moneyAmount) {
        Account account = accountRepo.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account with this ID: %s not found".formatted(accountId))
        );
        if (account.getMoneyAmount() < moneyAmount) {
            throw new RuntimeException("No such money to withdraw from account ID: %s".formatted(accountId));
        }
        account.setMoneyAmount(account.getMoneyAmount() - moneyAmount);
        return accountRepo.save(account).getId();
    }

    public String transferMoneyAmount(int fromId, int toId, int moneyAmount) {
        Account accountFromTransfer = accountRepo.findById(fromId).orElseThrow(
                () -> new RuntimeException("Account with this ID: %s not found".formatted(fromId))
        );
        Account accountToTransfer = accountRepo.findById(toId).orElseThrow(
                () -> new RuntimeException("Account with this ID: %s not found".formatted(toId))
        );
        if (accountFromTransfer.getMoneyAmount() < moneyAmount) {
            throw new RuntimeException(
                    "No such money to transfer from account ID: %s to account ID: %d".formatted(fromId, toId)
            );
        }
        accountFromTransfer.setMoneyAmount(accountFromTransfer.getMoneyAmount() - moneyAmount);
        int amountToTransfer = (int) Math.round(moneyAmount * (1 - accountProperties.getTransferCommission()));
        accountToTransfer.setMoneyAmount(accountToTransfer.getMoneyAmount() + amountToTransfer);
        return "Amount %s with commission %s transferred from ID: %s to account ID: %s".formatted(
                moneyAmount,
                moneyAmount - amountToTransfer,
                fromId,
                toId
        );
    }

    public int closeAccount(int accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(
                () -> new RuntimeException("Account with this ID: %s not found".formatted(accountId))
        );
        if (userRepo.findById(account.getUserId()).get().getAccountList().size() < 2) {
            throw new RuntimeException("Can't close the only one account with this ID: %s".formatted(accountId));
        }
        userRepo.deleteAccountFromList(account);
        accountRepo.delete(account);
        return accountId;
    }
}
