package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormPage {
    CalendarComponent calendar = new CalendarComponent();

    // Elements
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement subjectsMenu = $(".subjects-auto-complete__menu");
    private final SelenideElement hobbiesContainer = $("#hobbiesWrapper");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement currentAddressTextArea = $("#currentAddress");
    private final SelenideElement stateInput = $("#react-select-3-input");
    private final SelenideElement cityInput = $("#react-select-4-input");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement modalTitle = $("#example-modal-sizes-title-lg");
    private final SelenideElement modalBody = $(".modal-body");
    private final SelenideElement userForm = $("#userForm");


    // Actions
    public AutomationPracticeFormPage openPage(String value) {
        open(value);

        return this;
    }

    public AutomationPracticeFormPage typeFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public AutomationPracticeFormPage typeLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public AutomationPracticeFormPage typeUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    public AutomationPracticeFormPage selectGender(String gender) {
        $("[value='" + gender + "']").click();

        return this;
    }

    public AutomationPracticeFormPage typeUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public AutomationPracticeFormPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();

        calendar.setDate(day, month, year);

        return this;
    }

    public AutomationPracticeFormPage selectSubject(String subject) {
        subjectsInput.setValue(subject.substring(0, 1));
        subjectsMenu.$(byText(subject)).click();
        return this;
    }

    public AutomationPracticeFormPage setHobbies(String value) {
        hobbiesContainer.$(byText(value)).click();

        return this;
    }


    public AutomationPracticeFormPage uploadPicture(String value) {
        uploadPictureInput.uploadFromClasspath(value);

        return this;
    }

    public AutomationPracticeFormPage currentAddressTextArea(String value) {
        currentAddressTextArea.setValue(value);

        return this;
    }

    public AutomationPracticeFormPage selectState(String value) {
        stateInput.setValue(value).pressEnter();

        return this;
    }

    public AutomationPracticeFormPage selectCity(String value) {
        cityInput.setValue(value).pressEnter();

        return this;
    }


    public AutomationPracticeFormPage submitForm() {
        submitButton.click();

        return this;
    }

    public AutomationPracticeFormPage checkModalField(String fieldName, String expectedValue) {
        modalTitle.shouldBe(visible);

        modalBody.$(byText(fieldName))
                .parent()
                .$$("td").get(1)
                .shouldHave(exactText(expectedValue));

        return this;
    }

    public AutomationPracticeFormPage checkFormValidated() {
        userForm.shouldHave(Condition.cssClass("was-validated"));

        return this;
    }

    public AutomationPracticeFormPage checkModalIsNotVisible() {
        modalTitle.shouldNotBe(visible);

        return this;
    }
}
