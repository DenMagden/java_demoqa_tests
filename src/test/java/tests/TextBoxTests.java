package tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testdata.TestData.*;

public class TextBoxTests extends TestBase {

    @Test
    void successfulTestRegistrationFormAllFields() {
        open(URL_TEXT_BOX);
        $("#userName").setValue(USER_NAME);
        $("#userEmail").setValue(USER_EMAIL);
        $("#currentAddress").setValue(CURRENT_ADDRESS);
        $("#permanentAddress").setValue(PERMANENT_ADDRESS);
        $("#submit").click();

        $("#output #name").shouldHave(Condition.text("Name:" + USER_NAME));
        $("#output #email").shouldHave(Condition.text("Email:" + USER_EMAIL));
        $("#output #currentAddress").shouldHave(Condition.text("Current Address :" + CURRENT_ADDRESS));
        $("#output #permanentAddress").shouldHave(Condition.text("Permananet Address :" + PERMANENT_ADDRESS));
    }

    @Test
    void negativeTestRegistrationFormEmail() {
        open(URL_TEXT_BOX);
        $("#userEmail").setValue(USER_EMAIL_INCORRECT);
        $("#submit").click();

        $("#userEmail").shouldHave(Condition.cssClass("field-error"));
    }
}
