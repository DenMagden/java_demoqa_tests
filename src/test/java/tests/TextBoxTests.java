package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static testdata.DataGenerator.generateCurrentAddress;
import static testdata.DataGenerator.generateEmail;
import static testdata.DataGenerator.generateIncorrectEmail;
import static testdata.DataGenerator.generatePermanentAddress;
import static testdata.DataGenerator.generateUserName;
import static testdata.TestData.URL_TEXT_BOX;


public class TextBoxTests extends TestBase {

    private String userName;
    private String userEmail;
    private String currentAddress;
    private String permanentAddress;
    private String userEmailIncorrect;

    @BeforeEach
    void prepareRandomData() {
        userName = generateUserName();
        userEmail = generateEmail();
        currentAddress =  generateCurrentAddress();
        permanentAddress = generatePermanentAddress();
        userEmailIncorrect = generateIncorrectEmail();
    }

    @Test
    void successfulTestRegistrationFormAllFields() {
        textBoxPage
                .openPage(URL_TEXT_BOX)
                .typeUserName(String.valueOf(userName))
                .typeUserEmail(userEmail)
                .typeCurrentAddress(currentAddress)
                .typePermanentAddress(permanentAddress)
                .submitForm()
                .checkField("name", userName)
                .checkField("email", userEmail)
                .checkField("currentAddress", currentAddress)
                .checkField("permanentAddress", permanentAddress);
    }

    @Test
    void negativeTestRegistrationFormEmail() {
        textBoxPage
                .openPage(URL_TEXT_BOX)
                .typeUserEmail(userEmailIncorrect)
                .submitForm()
                .checkFieldError("userEmail");
    }
}
