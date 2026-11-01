package tests;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static testdata.TestData.*;


public class TextBoxTests extends TestBase {

    Faker faker = new Faker();
    Faker fakerRu = new Faker(new Locale("ru"));

    String USER_NAME;
    String USER_EMAIL;
    String CURRENT_ADDRESS;



    @BeforeEach
    void prepareRandomData() {
        USER_NAME = fakerRu.name().firstName();
        USER_EMAIL = faker.internet().emailAddress();

    }


    @Test
    void successfulTestRegistrationFormAllFields() {
        textBoxPage
                .openPage(URL_TEXT_BOX)
                .typeUserName(String.valueOf(USER_NAME))
                .typeUserEmail(USER_EMAIL)
                .typeCurrentAddress(CURRENT_ADDRESS)
                .typePermanentAddress(PERMANENT_ADDRESS)
                .submitForm()
                .checkField("name", USER_NAME)
                .checkField("email", USER_EMAIL)
                .checkField("currentAddress", CURRENT_ADDRESS)
                .checkField("permanentAddress", PERMANENT_ADDRESS);
    }

    @Test
    void negativeTestRegistrationFormEmail() {
        textBoxPage
                .openPage(URL_TEXT_BOX)
                .typeUserEmail(USER_EMAIL_INCORRECT)
                .submitForm()
                .checkFieldError("userEmail");
    }
}
