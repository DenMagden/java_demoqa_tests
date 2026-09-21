package tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormTests extends TestBase {

    @Test
    void successfulTestRegistrationFormAllFields () {
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Den");
        $("[id=lastName]").setValue("Magden");
        $("[id=userEmail]").setValue("denmagden@gmail.com");
        $("[value=Male]").click();
        $("[id=userNumber]").setValue("7999333221");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("[id=subjectsInput]").setValue("E");
        $(".subjects-auto-complete__menu").$(byText("English")).click();
        $("[id=hobbies-checkbox-1]").click();
        $("[id=hobbies-checkbox-2]").click();
        $("[id=uploadPicture]").uploadFromClasspath("test.png");
        $("[id=currentAddress]").setValue("Novosibirsk");
        $("[id=state]").scrollTo();
        $("[id=state]").click();
        $("[id=react-select-3-option-0]").click();
        $("[id=city]").click();
        $("[id=react-select-4-option-1]").click();
        $("[id=submit]").click();

        $("[id=example-modal-sizes-title-lg]").shouldBe();
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
    void successfulTestRegistrationFormRequiredFields () {
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Den");
        $("[id=lastName]").setValue("Magden");
        $("[value=Male]").click();
        $("[id=userNumber]").setValue("7999333221");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=example-modal-sizes-title-lg]").shouldBe();
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
    void negativeTestRegistrationFormWithoutFirstName () {
        open("https://demoqa.com/automation-practice-form");
        $("[id=lastName]").setValue("Magden");
        $("[value=Male]").click();
        $("[id=userNumber]").setValue("7999333221");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=userForm]").shouldHave(Condition.cssClass("was-validated"));
        $("[id=example-modal-sizes-title-lg]").shouldNotBe();
    }

    @Test
    void negativeTestRegistrationFormWithIncompleteNumber () {
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Den");
        $("[id=lastName]").setValue("Magden");
        $("[value=Male]").click();
        $("[id=userNumber]").setValue("799933322");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=userForm]").shouldHave(Condition.cssClass("was-validated"));
        $("[id=example-modal-sizes-title-lg]").shouldNotBe();
    }

    @Test
    void negativeTestRegistrationFormWithIncorrectNumber () {
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Den");
        $("[id=lastName]").setValue("Magden");
        $("[value=Male]").click();
        $("[id=userNumber]").setValue("qawdrgdcbd");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=userForm]").shouldHave(Condition.cssClass("was-validated"));
        $("[id=example-modal-sizes-title-lg]").shouldNotBe();
    }

    @Test
    void negativeTestRegistrationFormWithoutLastName () {
        open("https://demoqa.com/automation-practice-form");
        $("[id=firstName]").setValue("Den");
        $("[value=Male]").click();
        $("[id=userNumber]").setValue("7999333221");
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption("2000");
        $(".react-datepicker__month-select").selectOption("June");
        $(".react-datepicker__day--015").click();
        $("[id=submit]").scrollTo();
        $("[id=submit]").click();

        $("[id=userForm]").shouldHave(Condition.cssClass("was-validated"));
        $("[id=example-modal-sizes-title-lg]").shouldNotBe();
    }
}
