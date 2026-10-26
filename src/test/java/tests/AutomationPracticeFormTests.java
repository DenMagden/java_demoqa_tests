package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static testdata.TestData.*;

public class AutomationPracticeFormTests extends TestBase {

    @Test
    void successfulTestRegistrationFormAllFields() {
        open(URL_PRACTICE_FORM);
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("#userEmail").setValue(USER_EMAIL);
        $("[value=" + GENDER + "]").click();
        $("#userNumber").setValue(PHONE);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(YEAR);
        $(".react-datepicker__month-select").selectOption(MONTH);
        $(".react-datepicker__day--0" + DAY).click();
        $("#subjectsInput").setValue(FIRST_LETTER_SUBJECT);
        $(".subjects-auto-complete__menu").$(byText(SUBJECT)).click();
        $("#hobbiesWrapper").$(byText(HOBBY_1)).click();
        $("#hobbiesWrapper").$(byText(HOBBY_2)).click();
        $("#uploadPicture").uploadFromClasspath(PICTURE);
        $("#currentAddress").setValue(CURRENT_ADDRESS);
        $("#react-select-3-input").setValue(CITY_1).pressEnter();
        $("#react-select-4-input").setValue(CITY_2).pressEnter();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldBe();
        SelenideElement modal = $(".modal-body");
        modal.$(byText("Student Name")).parent().shouldHave(Condition.text(FIRST_NAME + " " + LAST_NAME));
        modal.$(byText("Student Email")).parent().shouldHave(Condition.text(USER_EMAIL));
        modal.$(byText("Gender")).parent().shouldHave(Condition.text(GENDER));
        modal.$(byText("Mobile")).parent().shouldHave(Condition.text(PHONE));
        modal.$(byText("Date of Birth")).parent().shouldHave(Condition.text(DAY + " " + MONTH + "," + YEAR));
        modal.$(byText("Subjects")).parent().shouldHave(Condition.text(SUBJECT));
        modal.$(byText("Hobbies")).parent().shouldHave(Condition.text(HOBBY_1 + ", " + HOBBY_2));
        modal.$(byText("Picture")).parent().shouldHave(Condition.text(PICTURE));
        modal.$(byText("Address")).parent().shouldHave(Condition.text(CURRENT_ADDRESS));
        modal.$(byText("State and City")).parent().shouldHave(Condition.text(CITY_1 + " " + CITY_2));
    }

    @Test
    void successfulTestRegistrationFormRequiredFields() {
        open(URL_PRACTICE_FORM);
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("[value=" + GENDER + "]").click();
        $("#userNumber").setValue(PHONE);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(YEAR);
        $(".react-datepicker__month-select").selectOption(MONTH);
        $(".react-datepicker__day--0" + DAY).click();
        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldBe();
        SelenideElement modal = $(".modal-body");
        modal.$(byText("Student Name")).parent().shouldHave(Condition.text(FIRST_NAME + " " + LAST_NAME));
        modal.$(byText("Student Email")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("Gender")).parent().shouldHave(Condition.text(GENDER));
        modal.$(byText("Mobile")).parent().shouldHave(Condition.text(PHONE));
        modal.$(byText("Date of Birth")).parent().shouldHave(Condition.text(DAY + " " + MONTH + "," + YEAR));
        modal.$(byText("Subjects")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("Hobbies")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("Picture")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("Address")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("State and City")).parent().$$("td").get(1).shouldHave(Condition.empty);
    }

    @Test
    void negativeTestRegistrationFormWithoutFirstName() {
        open(URL_PRACTICE_FORM);
        $("#lastName").setValue(LAST_NAME);
        $("[value=" + GENDER + "]").click();
        $("#userNumber").setValue(PHONE);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(YEAR);
        $(".react-datepicker__month-select").selectOption(MONTH);
        $(".react-datepicker__day--0" + DAY).click();
        $("#submit").scrollTo().click();

        $("#userForm").shouldHave(Condition.cssClass("was-validated"));
        $("#example-modal-sizes-title-lg").shouldNotBe();
    }

    @Test
    void negativeTestRegistrationFormWithIncompleteNumber() {
        open(URL_PRACTICE_FORM);
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("[value=" + GENDER + "]").click();
        $("#userNumber").setValue(PHONE);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(YEAR);
        $(".react-datepicker__month-select").selectOption(MONTH);
        $(".react-datepicker__day--0" + DAY).click();
        $("#submit").scrollTo().click();

        $("#userForm").shouldHave(Condition.cssClass("was-validated"));
        $("#example-modal-sizes-title-lg").shouldNotBe();
    }

    @Test
    void negativeTestRegistrationFormWithIncorrectNumber() {
        open(URL_PRACTICE_FORM);
        $("#firstName").setValue(FIRST_NAME);
        $("#lastName").setValue(LAST_NAME);
        $("[value=" + GENDER + "]").click();
        $("#userNumber").setValue(PHONE_INCORRECT);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(YEAR);
        $(".react-datepicker__month-select").selectOption(MONTH);
        $(".react-datepicker__day--0" + DAY).click();
        $("#submit").scrollTo().click();

        $("#userForm").shouldHave(Condition.cssClass("was-validated"));
        $("#example-modal-sizes-title-lg").shouldNotBe();
    }

    @Test
    void negativeTestRegistrationFormWithoutLastName() {
        open(URL_PRACTICE_FORM);
        $("#firstName").setValue(FIRST_NAME);
        $("[value=" + GENDER + "]").click();
        $("#userNumber").setValue(PHONE);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(YEAR);
        $(".react-datepicker__month-select").selectOption(MONTH);
        $(".react-datepicker__day--0" + DAY).click();
        $("#submit").scrollTo().click();

        $("#userForm").shouldHave(Condition.cssClass("was-validated"));
        $("#example-modal-sizes-title-lg").shouldNotBe();
    }
}
