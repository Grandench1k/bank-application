package grandench1k.springcorehomework.repo;

import grandench1k.springcorehomework.model.account.Account;
import grandench1k.springcorehomework.model.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepo {
    private final List<User> users;
    private int idCounter;

    public UserRepo() {
        users = new ArrayList<>();
        idCounter = 0;
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(int id) {
        Optional<User> user = Optional.empty();
        for (User userFromRepo : users) {
            if (userFromRepo.getId() == id) {
                user = Optional.of(userFromRepo);
            }
        }
        return user;
    }

    public boolean existsByLogin(String login) {
        for (User userFromRepo : users) {
            if (userFromRepo.getLogin().equals(login)) {
                return true;
            }
        }
        return false;

    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idCounter++);
            users.add(user);
            return user;
        } else {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId() == user.getId()) {
                    users.set(i, user);
                    return user;
                }
            }
            users.add(user);
            return user;
        }
    }

    public void deleteAccountFromList(Account account) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == account.getUserId()) {
                User user = users.get(i);
                ArrayList<Account> newAccountList = user.getAccountList();
                newAccountList.remove(account);
                user.setAccountList(newAccountList);
                users.set(i, user);
            }
        }
    }
}
