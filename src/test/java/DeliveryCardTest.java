
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {


    @Test
    public void shouldTest() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id='city'] input").setValue("Нарьян-Мар");
        $("[data-test-id='date'] input").sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        form.$("[data-test-id='date'] input").setValue(date);
        form.$("[data-test-id='name'] input").setValue("Василий");
        form.$("[data-test-id='phone'] input").setValue("+79270000000");
        form.$("[data-test-id='agreement']").click();
        form.$(".button").click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + date));
    }

}

