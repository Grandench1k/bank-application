package grandench1k.springcorehomework.service;

import grandench1k.springcorehomework.model.account.Account;
import grandench1k.springcorehomework.model.user.User;
import grandench1k.springcorehomework.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final AccountService accountService;

    public UserService(UserRepo userRepo, AccountService accountService) {
        this.userRepo = userRepo;
        this.accountService = accountService;
    }

    public User createUser(String login) {
        if (userRepo.existsByLogin(login)) {
            throw new RuntimeException("User with this login: %s already exists".formatted(login));
        }
        User user = new User();
        user.setLogin(login);
        ArrayList<Account> accounts = new ArrayList<>();
        User userWithId = userRepo.save(user);
        accounts.add(accountService.saveAccount(userWithId.getId()));
        userWithId.setAccountList(accounts);
        return userRepo.save(userWithId);
    }

    public List<User> findAllUsers() {
        List<User> userList = userRepo.findAll();
        if (userList.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return userList;
    }
}
