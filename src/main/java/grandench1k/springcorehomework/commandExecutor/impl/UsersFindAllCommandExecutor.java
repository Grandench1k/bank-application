package grandench1k.springcorehomework.commandExecutor.impl;

import grandench1k.springcorehomework.commandExecutor.CommandExecutor;
import grandench1k.springcorehomework.constant.ConsoleOperationType;
import grandench1k.springcorehomework.model.user.User;
import grandench1k.springcorehomework.service.UserService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsersFindAllCommandExecutor implements CommandExecutor {
    private final UserService userService;

    public UsersFindAllCommandExecutor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute() {
        try {
            List<User> userList = userService.findAllUsers();
            System.out.printf("List of all users: %s%n", userList.toString());
        } catch (RuntimeException e) {
            System.out.println("No users found");
        }
    }

    @Override
    public String getOperationType() {
        return ConsoleOperationType.SHOW_ALL_USERS.toString();
    }
}
