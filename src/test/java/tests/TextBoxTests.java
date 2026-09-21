package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends TestBase {

    @Test
    void successfulTestRegistrationFormAllFields() {
        open("https://demoqa.com/text-box");
        $("#userName").setValue("Den Magden");
        $("#userEmail").setValue("denmagden@gmail.com");
        $("#currentAddress").setValue("Novosibirsk");
        $("#permanentAddress").setValue("Nsk");
        $("#submit").click();

        $("#output #name").shouldHave(Condition.text("Name:Den Magden"));
        $("#output #email").shouldHave(Condition.text("Email:denmagden@gmail.com"));
        $("#output #currentAddress").shouldHave(Condition.text("Current Address :Novosibirsk"));
        $("#output #permanentAddress").shouldHave(Condition.text("Permananet Address :Nsk"));
    }

    @Test
    void negativeTestRegistrationFormEmail() {
        open("https://demoqa.com/text-box");
        $("#userEmail").setValue(".c");
        $("#submit").click();

        $("#userEmail").shouldHave(Condition.cssClass("field-error"));
    }
}
