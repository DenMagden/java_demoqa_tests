package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ResultTableComponent {

    private final SelenideElement modalBody = $(".modal-body");
    private final SelenideElement modalTitle = $("#example-modal-sizes-title-lg");

    public void checkField(String fieldName, String expectedValue) {
        modalTitle.shouldBe(visible);

        modalBody.$(byText(fieldName))
                .parent()
                .$$("td").get(1)
                .shouldHave(exactText(expectedValue));
    }

    public void shouldNotBeVisible() {
        modalTitle.shouldNotBe(visible);
    }
}
