package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends TestBase {

    @Test
    void successfulTestRegistrationFormAllFields () {
        open("https://demoqa.com/text-box");
        $("[id=userName]").setValue("Den Magden");
        $("[id=userEmail]").setValue("denmagden@gmail.com");
        $("[id=currentAddress]").setValue("Novosibirsk");
        $("[id=permanentAddress]").setValue("Nsk");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(Condition.text("Name:Den Magden"));
        $("[id=output] [id=email]").shouldHave(Condition.text("Email:denmagden@gmail.com"));
        $("[id=output] [id=currentAddress]").shouldHave(Condition.text("Current Address :Novosibirsk"));
        $("[id=output] [id=permanentAddress]").shouldHave(Condition.text("Permananet Address :Nsk"));
    }

    @Test
    void negativeTestRegistrationFormEmail () {
        open("https://demoqa.com/text-box");
        $("[id=userEmail]").setValue(".c");
        $("[id=submit]").click();

        $("[id=userEmail]").shouldHave(Condition.cssClass("field-error"));
    }
}
