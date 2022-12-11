import UI.UserOperations;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertTrue;

public class UserLoginTest {

    Map<String, String> user = new UserOperations().register();
    String email = user.get("email");
    String password = user.get("password");
    Main main;
    private WebDriver driver;

    @Before
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        main = open(Main.URL, Main.class);
    }

    @Test
    @Description("Авторизация пользователя. Кнопка 'Войти в аккаунт'")
    public void loginUserWithLoginButtonTest(){

        main
                .clickLoginButton()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        boolean buttonShow = main.arrangeOrderButtonVisible();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", buttonShow);

    }

    @Test
    @Description ("Авторизация пользователя. Кнопка 'Личный кабинет'")
    public void loginUserWithCabinetButtonTest(){

        main
                .clickCabinetButton()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        boolean buttonShow = main.arrangeOrderButtonVisible();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", buttonShow);
    }

    @Test
    @Description ("Авторизация пользователя. Кнопка 'Войти' на странице регистрации")
    public void loginUserWithLoginButtonInRegPageTest(){

        main
                .clickLoginButton()
                .regLinkClick()
                .loginLinkClick()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        boolean buttonShow = main.arrangeOrderButtonVisible();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", buttonShow);

    }

    @Test
    @Description ("Авторизация пользователя. Кнопка 'Войти' страница востановления пароля")
    public void loginUserWithResetPasswordLinkTest(){

        main
                .clickLoginButton()
                .resetPasswordLinkClick()
                .loginLinkClick()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        boolean buttonShow = main.arrangeOrderButtonVisible();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", buttonShow);

    }

    @Test
    @Description ("Выход пользователя")
    public void logoutUserTest(){

        main
                .clickLoginButton()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick()
                .clickCabinetButton()
                .exitButtonClick();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
    }

    @After //Удаляем созданого пользователя
    public void cleanUp() {
        UserOperations.delete();
        driver.quit();
    }
}
