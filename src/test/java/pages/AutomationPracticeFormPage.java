package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormPage {
    CalendarComponent calendar = new CalendarComponent();
    ResultTableComponent resultTable = new ResultTableComponent();

    // Elements
    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            userNumberInput = $("#userNumber"),
            dateOfBirthInput = $("#dateOfBirthInput"),
            subjectsInput = $("#subjectsInput"),
            subjectsMenu = $(".subjects-auto-complete__menu"),
            hobbiesContainer = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressTextArea = $("#currentAddress"),
            stateInput = $("#react-select-3-input"),
            cityInput = $("#react-select-4-input"),
            submitButton = $("#submit"),
            modalTitle = $("#example-modal-sizes-title-lg"),
            modalBody = $(".modal-body"),
            userForm = $("#userForm");

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

    public AutomationPracticeFormPage setDateOfBirth(LocalDate date) {
        dateOfBirthInput.click();

        calendar.setDate(String.format("%02d", date.getDayOfMonth()),
                date.getMonth().getDisplayName(
                        TextStyle.FULL, Locale.ENGLISH),
                String.valueOf(date.getYear()));

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

    public AutomationPracticeFormPage checkModalField(
            String fieldName, String expectedValue) {
        resultTable.checkField(fieldName, expectedValue);

        return this;
    }

    public AutomationPracticeFormPage checkFormValidated() {
        userForm.shouldHave(Condition.cssClass("was-validated"));

        return this;
    }

    public AutomationPracticeFormPage checkModalIsNotVisible() {
        resultTable.shouldNotBeVisible();
        return this;
    }
}
