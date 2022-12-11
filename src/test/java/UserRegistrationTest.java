import UI.RegistrationPage;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.example.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserRegistrationTest {

    RegistrationPage registrationPage = page(RegistrationPage.class);
    Main main;

    public static Faker faker = new Faker();
    String email = faker.name().lastName() + "@yandex.ru";
    String password = faker.internet().password();
    String name = faker.name().firstName();
    String shortPassword = faker.internet().password(1, 5);

    private WebDriver driver;

    @Before
    public void before() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        main = open(Main.URL, Main.class);
    }

    @Test
    @Description ("Регистрация нового пользоватля. Авторизация новым пользователем")
    public void userRegistrationTest (){

        main
                .clickLoginButton()
                .regLinkClick()
                .setName(name)
                .setEmail(email)
                .setPassword(password)
                .regButtonClick()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        boolean buttonShow = main.arrangeOrderButtonVisible();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", buttonShow);

    }

    @Test
    @Description ("Попытка регистрации нового пользоватля, пароль менее 6 мисволов")
    public void userRegistrationIncorrectPasswordTest (){

        String expectedErrorMessage = "Некорректный пароль";

        main
                .clickLoginButton()
                .regLinkClick()
                .setName(name)
                .setEmail(email)
                .setPassword(shortPassword)
                .regButtonClick();

        String actualErrorMessage = registrationPage.getPassErrorMessageText();

        assertEquals(expectedErrorMessage,actualErrorMessage);
    }

    @After
    public void cleanUp(){
        driver.quit();
    }
}