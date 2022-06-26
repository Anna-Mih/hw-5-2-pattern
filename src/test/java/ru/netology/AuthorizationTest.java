package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationTest {

        @BeforeEach
        public void connect() {
            open("http://localhost:9999/");
        }

        @Test
        public void shouldActiveUser() {
            UserInfo user = DataGenerator.generateUser("active");
            Registration.registerUser(user);
            $x("//input[@name= \"login\"]").val(user.getLogin());
            $x("//input[@name= \"password\"]").val(user.getPassword());
            $x("//button[@data-test-id=\"action-login\"]").click();
            $x("//h2").should(text("Личный кабинет"));

        }

        @Test
        public void shouldBlockedUser() {
            UserInfo user = DataGenerator.generateUser("blocked");
            Registration.registerUser(user);
            $x("//input[@name= \"login\"]").val(user.getLogin());
            $x("//input[@name= \"password\"]").val(user.getPassword());
            $x("//button[@data-test-id=\"action-login\"]").click();
            $x("//div[@data-test-id = 'error-notification']").shouldBe(visible);
            $x("//div[@class = 'notification__content']").shouldBe(text("Пользователь заблокирован"));
        }

        @Test
        public void shouldSadPathInvalidLogin() {
            UserInfo user = DataGenerator.generateUser("blocked");
            Registration.registerUser(user);
            $x("//input[@name= \"login\"]").val(DataGenerator.generateLogin());
            $x("//input[@name= \"password\"]").val(user.getPassword());
            $x("//button[@data-test-id=\"action-login\"]").click();
            $x("//div[@class =\"notification__content\"] ").shouldBe(visible);
            $x("//div[@class =\"notification__content\"] ").shouldBe(text("Неверно указан логин или пароль"));
        }

        @Test
        public void shouldSadPathInvalidPassword() {
            UserInfo user = DataGenerator.generateUser("blocked");
            Registration.registerUser(user);
            $x("//input[@name= \"login\"]").val(user.getLogin());
            $x("//input[@name= \"password\"]").val(DataGenerator.generatePassword());
            $x("//button[@data-test-id=\"action-login\"]").click();
            $x("//div[@class =\"notification__content\"] ").shouldBe(visible);
            $x("//div[@class =\"notification__content\"] ").shouldBe(text("Неверно указан логин или пароль"));
        }
    }
