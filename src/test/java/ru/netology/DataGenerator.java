package ru.netology;

import com.github.javafaker.Faker;

public class DataGenerator {

    public static UserInfo generateUser(String status) {
        String login = generateLogin();
        String password = generatePassword();
        UserInfo user = new UserInfo(login, password, status);
        return user;
    }

    public static String generateLogin() {
        Faker faker = new Faker();
        String login = faker.name().username();
        return login;
    }

    public static String generatePassword() {
        Faker faker = new Faker();
        String password = faker.internet().password();
        return password;
    }
}
