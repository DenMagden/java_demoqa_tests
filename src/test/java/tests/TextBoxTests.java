package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;
import testdata.DataGenerator;

import static io.qameta.allure.Allure.step;
import static testdata.TestData.URL_TEXT_BOX;

@Epic("DemoQA")
@Feature("Форма регистрации малая")
public class TextBoxTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();
    private final DataGenerator dataGenerator = new DataGenerator();

    private String userName;
    private String userEmail;
    private String currentAddress;
    private String permanentAddress;
    private String userEmailIncorrect;

    @BeforeEach
    void prepareRandomData() {
        userName = dataGenerator.generateUserName();
        userEmail = dataGenerator.generateEmail();
        currentAddress = dataGenerator.generateCurrentAddress();
        permanentAddress = dataGenerator.generatePermanentAddress();
        userEmailIncorrect = dataGenerator.generateIncorrectEmail();
    }

    @Test
    @DisplayName("Успешная регистрация со всеми полями")
    void successfulTestRegistrationFormAllFields() {

        step("Открываем страницу " + URL_TEXT_BOX, () ->
                textBoxPage.openPage(URL_TEXT_BOX));

        step("Заполняем форму", () -> {
            textBoxPage
                    .typeUserName(String.valueOf(userName))
                    .typeUserEmail(userEmail)
                    .typeCurrentAddress(currentAddress)
                    .typePermanentAddress(permanentAddress)
                    .submitForm();
        });

        step("Проверяем результат", () -> {
            textBoxPage
                    .checkField("name", userName)
                    .checkField("email", userEmail)
                    .checkField("currentAddress", currentAddress)
                    .checkField("permanentAddress", permanentAddress);
        });
    }

    @Test
    @DisplayName("Невалидный email подсвечивается ошибкой")
    void negativeTestRegistrationFormEmail() {
        step("Открываем страницу " + URL_TEXT_BOX, () ->
                textBoxPage.openPage(URL_TEXT_BOX));

        step("Заполняем форму", () -> {
            textBoxPage
                    .typeUserEmail(userEmailIncorrect)
                    .submitForm();
        });

        step("Проверяем подсветку ошибки у email", () ->
                textBoxPage.checkFieldError("userEmail"));
    }
}
