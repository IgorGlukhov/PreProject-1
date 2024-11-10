package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Igor", "Gluhov", (byte) 42);
        userService.saveUser("Mike", "Mike", (byte) 42);
        userService.saveUser("Jack", "Jack", (byte) 42);
        userService.saveUser("David", "David", (byte) 42);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
