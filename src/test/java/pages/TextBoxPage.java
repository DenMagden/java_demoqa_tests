package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxPage {
    // Elements
    private final SelenideElement
            userNameInput = $("#userName"),
            userEmailInput = $("#userEmail"),
            currentAddressTextArea = $("#currentAddress"),
            permanentAddressTextArea = $("#permanentAddress"),
            submitButton = $("#submit"),
            outputResults = $("#output");

    // Actions
    public TextBoxPage openPage(String value) {
        open(value);

        return this;
    }

    @Step("Ввести имя и фамилию \"{value}\"")
    public TextBoxPage typeUserName(String value) {
        userNameInput.setValue(value);

        return this;
    }

    @Step("Ввести email \"{value}\"")
    public TextBoxPage typeUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Ввести текущий адрес \"{value}\"")
    public TextBoxPage typeCurrentAddress(String value) {
        currentAddressTextArea.setValue(value);

        return this;
    }

    @Step("Ввести постоянный адрес \"{value}\"")
    public TextBoxPage typePermanentAddress(String value) {
        permanentAddressTextArea.setValue(value);

        return this;
    }

    @Step("Отправить форму")
    public TextBoxPage submitForm() {
        submitButton.click();

        return this;
    }

    @Step("Проверяем результат: «{key}» = «{value}»")
    public TextBoxPage checkField(String key, String value) {
        outputResults.$(byId(key)).shouldHave(text(value));

        return this;
    }

    @Step("Проверяем ошибку для поля \"{fieldId}\"")
    public TextBoxPage checkFieldError(String fieldId) {
        $(byId(fieldId)).shouldHave(cssClass("field-error"));

        return this;
    }
}
