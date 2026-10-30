package tests;

import org.junit.jupiter.api.Test;

import static testdata.TestData.*;


public class TextBoxTests extends TestBase {


    @Test
    void successfulTestRegistrationFormAllFields() {
        textBoxPage.openPage(URL_TEXT_BOX)
                .typeUserName(USER_NAME)
                .typeUserEmail(USER_EMAIL)
                .typeCurrentAddress(CURRENT_ADDRESS)
                .typePermanentAddress(PERMANENT_ADDRESS)
                .submitForm();

        textBoxPage.checkField("name", USER_NAME);
        textBoxPage.checkField("email", USER_EMAIL);
        textBoxPage.checkField("currentAddress", CURRENT_ADDRESS);
        textBoxPage.checkField("permanentAddress", PERMANENT_ADDRESS);
    }

    @Test
    void negativeTestRegistrationFormEmail() {
        textBoxPage.openPage(URL_TEXT_BOX)
                .typeUserEmail(USER_EMAIL_INCORRECT)
                .submitForm();

        textBoxPage.checkFieldError("userEmail");
    }
}
