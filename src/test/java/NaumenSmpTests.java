import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class NaumenSmpTests {

    public static String getId() {
        // Генерация случайного уникального идентификатора
        return UUID.randomUUID().toString();
    }

    @Test
    public void addFavourite() {
        Configuration.browser = "chrome";
        Configuration.timeout = 13000; // Устанавливаем таймер 13 секунд
        Selenide.open("http://5.181.254.246:8080/sd"); //Откроем страницу для тестов
        Configuration.browserSize = "1920*992"; // Установим размер окна браузера

        // Ввод данных для входа в учетную запись
        $("#username").click();
        $("#username").type("user27");
        $("#password").type("02-Mi12Se2004An");
        $("#submit-button").click();

        String title = getId(); // Придумываем случайное название карточке страницы

        // Добавляем карточку в избранное с индивидуальным названием
        $("#gwt-debug-favorite").shouldBe(visible.because("Не найдена кнопка добавления в избранные, возможно не удалось войти в учетную запись"));
        $("#gwt-debug-favorite").click();
        $("#gwt-debug-itemTitle-value").type(String.valueOf(title));
        $("#gwt-debug-apply").click();

        // Проверяем открыто ли бокове меню, если нет, то открываем
        SelenideElement element = $("#gwt-debug-navContent"); //
        if (element.getCssValue("display").equals("none")) { //
            $("#gwt-debug-c5af86c7-6e4d-a611-55f9-d3fc8dcc236c").click(); //
        }

        $(byXpath(String.format("//a[@id='gwt-debug-title']/div[text()='%s']", title))).shouldBe(visible.because("Такой карточки нет в разделе избранных")); // Проверяем, что карточка создалась

        // Выходим из учетной записи
        $("#gwt-debug-logout").click();
    }

    @Test
    public void deleteFavourite() {
        Configuration.browser = "chrome";
        Configuration.timeout = 13000; // Устанавливаем таймер 13 секунд
        Selenide.open("http://5.181.254.246:8080/sd"); //Откроем страницу для тестов
        Configuration.browserSize = "1920*992"; // Установимм размер окна браузера

        // Ввод данных для входа в учетную запись
        $("#username").click();
        $("#username").type("user27");
        $("#password").type("02-Mi12Se2004An");
        $("#submit-button").click();

        String title = getId(); // Придумываем случайное название карточке страницы

        // Добавляем карточку в избранное с индивидуальным названием
        $("#gwt-debug-favorite").shouldBe(visible.because("Не найдена кнопка добавления в избранные, возможно не удалось войти в учетную запись"));
        $("#gwt-debug-favorite").click();
        $("#gwt-debug-itemTitle-value").type(String.valueOf(title));
        $("#gwt-debug-apply").click(); //

        // Проверяем открыто ли бокове меню6 если нет, то открываем
        SelenideElement element = $("#gwt-debug-navContent"); //
        if (element.getCssValue("display").equals("none")) { //
            $("#gwt-debug-c5af86c7-6e4d-a611-55f9-d3fc8dcc236c").click(); //
        }

        // Удаляем карточку страницы при наличии
        $(byXpath(String.format("//a[@id='gwt-debug-title']/div[text()='%s']", title))).shouldBe(visible.because("Такой карточки нет в разделе избранных")); // Проверяем, что карточка создалась
        $(byXpath(String.format("//a[@id='gwt-debug-title']/div[text()='%s']", title))).hover();
        $(byXpath(String.format("//a[@id='gwt-debug-title']/div[text()='%s']/../../div[@class='GFIRBY5J3']/span", title))).click();
        $("#gwt-debug-yes").click();

        // Выходим из учетной записи
        $("#gwt-debug-logout").click();
    }
}
