package grandench1k.springcorehomework.commandExecutor.impl;

import grandench1k.springcorehomework.commandExecutor.CommandExecutor;
import grandench1k.springcorehomework.constant.ConsoleOperationType;
import grandench1k.springcorehomework.model.user.User;
import grandench1k.springcorehomework.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserCreateCommandExecutor implements CommandExecutor {
    private final UserService userService;
    private final Scanner scanner;

    public UserCreateCommandExecutor(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter login for new user:");
        String userLogin = scanner.nextLine();
        try {
            User user = userService.createUser(userLogin);
            System.out.printf("User created: %s ", user.toString());
        } catch (RuntimeException e) {
            System.out.printf(e.getMessage());
        }

    }

    @Override
    public String getOperationType() {
        return ConsoleOperationType.USER_CREATE.toString();
    }
}
