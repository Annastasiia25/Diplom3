import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.example.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    Main main;

    @Before
    public void before() {
        Configuration.startMaximized = true;
        main = open(Main.URL, Main.class);
    }

    @Test
    @Description("Проверка что есть скролл к элементу конструктора 'Булки'. Блок 'Булки' отображается.")
    public void scrollWithBurgerElementTest(){

        main
                .fillingButtonClick()
                .bunButtonClick();

        boolean blockVisible = main.burgerBlock.isDisplayed();

        assertTrue("Block is invisible",blockVisible);
    }

    @Test
    @Description ("Проверка что есть скролл к элементу конструктора 'Соусы'. Блок 'Соусы' отображается.")
    public void scrollWithSauceElementTest(){

        main
                .sauceButtonClick();

        boolean blockVisible = main.sauceBlock.isDisplayed();

        assertTrue("Block is invisible",blockVisible);
    }

    @Test
    @Description ("Проверка что есть скролл к элементу конструктора 'Начинки'. Блок 'Начинки' отображается.")
    public void scrollWithFillingElementTest(){

        main
                .fillingButtonClick();

        boolean blockVisible = main.fillingBlock.isDisplayed();

        assertTrue("Block is invisible",blockVisible);
    }

    @After
    public void tearDown (){
        webdriver().driver().close();
    }
}
