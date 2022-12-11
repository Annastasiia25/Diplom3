import UI.UserOperations;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.example.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class PersonalOfficeUserTest {

    Map<String, String> user = new UserOperations().register();
    String email = user.get("email");
    String password = user.get("password");
    Main main;

    @Before
    public void before() {
        Configuration.startMaximized = true;
        main = open(Main.URL, Main.class);
    }

    @Test
    @Description("Нажатие кнопки 'Личный кабинет'. Авторизированный пользователь")
    public void openPersonalOfficePageTest() {

        main
                .clickLoginButton()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick()
                .clickCabinetButton();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/account/profile"));

    }

    @Test
    @Description("Нажатие кнопки 'Личный кабинет'. Не авторизированный пользователь")
    public void openPersonalOfficePageNonAuthUserTest() {

        main
                .clickCabinetButton();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));

    }

    @Test
    @Description("Нажатие кнопки 'Конструктор'. Редирект на главную")
    public void redirectConstructorButtonWithPersonalPageTest() {

        main
                .clickLoginButton()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick()
                .clickCabinetButton()
                .constructorButtonClick();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));

    }

    @Test
    @Description("Нажатие лого 'Stellar burger'. Редирект на главную")
    public void redirectBurgerLogoWithPersonalPageTest() {

        main
                .clickLoginButton()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick()
                .clickCabinetButton()
                .stellarBurgerClick();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));

    }

    @After //Удаляем созданого пользователя
    public void tearDown() {
        UserOperations.delete();
        webdriver().driver().close();
    }
}