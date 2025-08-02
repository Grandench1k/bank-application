package grandench1k.springcorehomework.repo;

import grandench1k.springcorehomework.model.account.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepo {
    private final List<Account> accounts;
    private int idCounter;

    public AccountRepo() {
        accounts = new ArrayList<Account>();
        idCounter = 0;
    }

    public Optional<Account> findById(int id) {
        Optional<Account> account = Optional.empty();
        for (Account accountFromRepo : accounts) {
            if (accountFromRepo.getId() == id) {
                account = Optional.of(accountFromRepo);
            }
        }
        return account;
    }

    public Account save(Account account) {
        if (account.getId() == null) {
            account.setId(idCounter++);
            accounts.add(account);
            return account;
        } else {
            for (int i = 0; i < accounts.size(); i++) {
                if (accounts.get(i).getId() == account.getId()) {
                    accounts.set(i, account);
                    return account;
                }
            }
            accounts.add(account);
            return account;
        }
    }

    public void delete(Account account) {
        accounts.remove(account);
    }
}
