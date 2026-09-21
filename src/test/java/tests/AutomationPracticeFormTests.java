package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormTests extends TestBase {

    @Test
    void successfulTestRegistrationFormAllFields() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Den");
        $("#lastName").setValue("Magden");
        $("#userEmail").setValue("denmagden@gmail.com");
        $("[value=Male]").click();
        $("#userNumber").setValue("7999333221");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("#subjectsInput").setValue("E");
        $(".subjects-auto-complete__menu").$(byText("English")).click();
        $("#hobbies-checkbox-1").click();
        $("#hobbies-checkbox-2").click();
        $("#uploadPicture").uploadFromClasspath("test.png");
        $("#currentAddress").setValue("Novosibirsk");
        $("#state").scrollTo();
        $("#state").click();
        $("#react-select-3-option-0").click();
        $("#city").click();
        $("#react-select-4-option-1").click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldBe();
        SelenideElement modal = $(".modal-body");
        modal.$(byText("Student Name")).parent().shouldHave(Condition.text("Den Magden"));
        modal.$(byText("Student Email")).parent().shouldHave(Condition.text("denmagden@gmail.com"));
        modal.$(byText("Gender")).parent().shouldHave(Condition.text("Male"));
        modal.$(byText("Mobile")).parent().shouldHave(Condition.text("7999333221"));
        modal.$(byText("Date of Birth")).parent().shouldHave(Condition.text("15 June,2000"));
        modal.$(byText("Subjects")).parent().shouldHave(Condition.text("English"));
        modal.$(byText("Hobbies")).parent().shouldHave(Condition.text("Sports, Reading"));
        modal.$(byText("Picture")).parent().shouldHave(Condition.text("test.png"));
        modal.$(byText("Address")).parent().shouldHave(Condition.text("Novosibirsk"));
        modal.$(byText("State and City")).parent().shouldHave(Condition.text("NCR Gurgaon"));
    }

    @Test
    void successfulTestRegistrationFormRequiredFields() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Den");
        $("#lastName").setValue("Magden");
        $("[value=Male]").click();
        $("#userNumber").setValue("7999333221");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("#submit").scrollTo();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldBe();
        SelenideElement modal = $(".modal-body");
        modal.$(byText("Student Name")).parent().shouldHave(Condition.text("Den Magden"));
        modal.$(byText("Student Email")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("Gender")).parent().shouldHave(Condition.text("Male"));
        modal.$(byText("Mobile")).parent().shouldHave(Condition.text("7999333221"));
        modal.$(byText("Date of Birth")).parent().shouldHave(Condition.text("15 June,2000"));
        modal.$(byText("Subjects")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("Hobbies")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("Picture")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("Address")).parent().$$("td").get(1).shouldHave(Condition.empty);
        modal.$(byText("State and City")).parent().$$("td").get(1).shouldHave(Condition.empty);
    }

    @Test
    void negativeTestRegistrationFormWithoutFirstName() {
        open("https://demoqa.com/automation-practice-form");
        $("#lastName").setValue("Magden");
        $("[value=Male]").click();
        $("#userNumber").setValue("7999333221");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("#submit").scrollTo();
        $("#submit").click();

        $("#userForm").shouldHave(Condition.cssClass("was-validated"));
        $("#example-modal-sizes-title-lg").shouldNotBe();
    }

    @Test
    void negativeTestRegistrationFormWithIncompleteNumber() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Den");
        $("#lastName").setValue("Magden");
        $("[value=Male]").click();
        $("#userNumber").setValue("799933322");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("#submit").scrollTo();
        $("#submit").click();

        $("#userForm").shouldHave(Condition.cssClass("was-validated"));
        $("#example-modal-sizes-title-lg").shouldNotBe();
    }

    @Test
    void negativeTestRegistrationFormWithIncorrectNumber() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Den");
        $("#lastName").setValue("Magden");
        $("[value=Male]").click();
        $("#userNumber").setValue("qawdrgdcbd");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("#submit").scrollTo();
        $("#submit").click();

        $("#userForm").shouldHave(Condition.cssClass("was-validated"));
        $("#example-modal-sizes-title-lg").shouldNotBe();
    }

    @Test
    void negativeTestRegistrationFormWithoutLastName() {
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Den");
        $("[value=Male]").click();
        $("#userNumber").setValue("7999333221");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("#submit").scrollTo();
        $("#submit").click();

        $("#userForm").shouldHave(Condition.cssClass("was-validated"));
        $("#example-modal-sizes-title-lg").shouldNotBe();
    }
}
