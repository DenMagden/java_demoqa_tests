package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testdata.DataGenerator;

import static testdata.TestData.URL_TEXT_BOX;


public class TextBoxTests extends TestBase {

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
        currentAddress =  dataGenerator.generateCurrentAddress();
        permanentAddress = dataGenerator.generatePermanentAddress();
        userEmailIncorrect = dataGenerator.generateIncorrectEmail();
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
