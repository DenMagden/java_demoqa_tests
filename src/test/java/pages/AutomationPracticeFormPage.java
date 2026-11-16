package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

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
            userForm = $("#userForm");

    // Actions
    public AutomationPracticeFormPage openPage(String value) {
        open(value);

        return this;
    }

    @Step("Ввести имя \"{value}\"")
    public AutomationPracticeFormPage typeFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    @Step("Ввести фамилию \"{value}\"")
    public AutomationPracticeFormPage typeLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    @Step("Ввести email \"{value}\"")
    public AutomationPracticeFormPage typeUserEmail(String value) {
        userEmailInput.setValue(value);

        return this;
    }

    @Step("Выбрать пол \"{value}\"")
    public AutomationPracticeFormPage selectGender(String gender) {
        $("[value='" + gender + "']").click();

        return this;
    }

    @Step("Ввести номер телефона \"{value}\"")
    public AutomationPracticeFormPage typeUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    @Step("Выбрать дату рождения \"{date}\"")
    public AutomationPracticeFormPage setDateOfBirth(LocalDate date) {
        dateOfBirthInput.click();

        calendar.setDate(String.format("%02d", date.getDayOfMonth()),
                date.getMonth().getDisplayName(
                        TextStyle.FULL, Locale.ENGLISH),
                String.valueOf(date.getYear()));

        return this;
    }

    @Step("Выбрать предмет \"{value}\"")
    public AutomationPracticeFormPage selectSubject(String subject) {
        subjectsInput.setValue(subject.substring(0, 1));
        subjectsMenu.$(byText(subject)).click();
        return this;
    }

    @Step("Выбрать хобби \"{value}\"")
    public AutomationPracticeFormPage setHobbies(String value) {
        hobbiesContainer.$(byText(value)).click();

        return this;
    }

    @Step("Загрузить картинку \"{value}\"")
    public AutomationPracticeFormPage uploadPicture(String value) {
        uploadPictureInput.uploadFromClasspath(value);

        return this;
    }

    @Step("Ввести текущий адрес \"{value}\"")
    public AutomationPracticeFormPage currentAddressTextArea(String value) {
        currentAddressTextArea.setValue(value);

        return this;
    }

    @Step("Выбрать штат \"{value}\"")
    public AutomationPracticeFormPage selectState(String value) {
        stateInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Выбрать город \"{value}\"")
    public AutomationPracticeFormPage selectCity(String value) {
        cityInput.setValue(value).pressEnter();

        return this;
    }

    @Step("Отправить форму")
    public AutomationPracticeFormPage submitForm() {
        submitButton.click();

        return this;
    }

    @Step("Проверяем итоговую форму: «{fieldName}» = «{expectedValue}»")
    public AutomationPracticeFormPage checkModalField(String fieldName, String expectedValue) {
        resultTable.checkField(fieldName, expectedValue);

        return this;
    }

    @Step("Проверяем валидацию итоговой формы")
    public AutomationPracticeFormPage checkFormValidated() {
        userForm.shouldHave(Condition.cssClass("was-validated"));

        return this;
    }

    @Step("Проверяем, что итоговой формы нет")
    public AutomationPracticeFormPage checkModalIsNotVisible() {
        resultTable.shouldNotBeVisible();
        return this;
    }
}
