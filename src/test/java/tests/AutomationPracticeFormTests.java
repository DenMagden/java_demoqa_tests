package tests;

import org.junit.jupiter.api.Test;

import static testdata.TestData.CITY_1;
import static testdata.TestData.CITY_2;
import static testdata.TestData.CURRENT_ADDRESS;
import static testdata.TestData.DAY;
import static testdata.TestData.FIRST_NAME;
import static testdata.TestData.GENDER;
import static testdata.TestData.HOBBY_1;
import static testdata.TestData.HOBBY_2;
import static testdata.TestData.LAST_NAME;
import static testdata.TestData.MONTH;
import static testdata.TestData.PHONE;
import static testdata.TestData.PHONE_INCOMPLETE;
import static testdata.TestData.PHONE_INCORRECT;
import static testdata.TestData.PICTURE;
import static testdata.TestData.SUBJECT;
import static testdata.TestData.URL_PRACTICE_FORM;
import static testdata.TestData.USER_EMAIL;
import static testdata.TestData.YEAR;

public class AutomationPracticeFormTests extends TestBase {

    @Test
    void successfulTestRegistrationFormAllFields() {
        automationPracticeFormPage
                .openPage(URL_PRACTICE_FORM)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .typeUserEmail(USER_EMAIL)
                .selectGender(GENDER)
                .typeUserNumber(PHONE)
                .setDateOfBirth(DAY, MONTH, YEAR)
                .selectSubject(SUBJECT)
                .setHobbies(HOBBY_1)
                .setHobbies(HOBBY_2)
                .uploadPicture(PICTURE)
                .currentAddressTextArea(CURRENT_ADDRESS)
                .selectState(CITY_1)
                .selectCity(CITY_2)
                .submitForm()
                .checkModalField("Student Name", FIRST_NAME + " " + LAST_NAME)
                .checkModalField("Student Email", USER_EMAIL)
                .checkModalField("Gender", GENDER)
                .checkModalField("Mobile", PHONE)
                .checkModalField("Date of Birth", DAY + " " + MONTH + "," + YEAR)
                .checkModalField("Subjects", SUBJECT)
                .checkModalField("Hobbies", HOBBY_1 + ", " + HOBBY_2)
                .checkModalField("Picture", PICTURE)
                .checkModalField("Address", CURRENT_ADDRESS)
                .checkModalField("State and City", CITY_1 + " " + CITY_2);
    }

    @Test
    void successfulTestRegistrationFormRequiredFields() {
        automationPracticeFormPage
                .openPage(URL_PRACTICE_FORM)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .selectGender(GENDER)
                .typeUserNumber(PHONE)
                .setDateOfBirth(DAY, MONTH, YEAR)
                .submitForm()
                .checkModalField("Student Name", FIRST_NAME + " " + LAST_NAME)
                .checkModalField("Student Email", "")
                .checkModalField("Gender", GENDER)
                .checkModalField("Mobile", PHONE)
                .checkModalField("Date of Birth", DAY + " " + MONTH + "," + YEAR)
                .checkModalField("Subjects", "")
                .checkModalField("Hobbies", "")
                .checkModalField("Picture", "")
                .checkModalField("Address", "")
                .checkModalField("State and City", "");
    }

    @Test
    void negativeTestRegistrationFormWithoutFirstName() {
        automationPracticeFormPage
                .openPage(URL_PRACTICE_FORM)
                .typeLastName(LAST_NAME)
                .selectGender(GENDER)
                .typeUserNumber(PHONE)
                .setDateOfBirth(DAY, MONTH, YEAR)
                .submitForm()
                .checkFormValidated()
                .checkModalIsNotVisible();
    }

    @Test
    void negativeTestRegistrationFormWithIncompleteNumber() {
        automationPracticeFormPage
                .openPage(URL_PRACTICE_FORM)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .selectGender(GENDER)
                .typeUserNumber(PHONE_INCOMPLETE)
                .setDateOfBirth(DAY, MONTH, YEAR)
                .submitForm()
                .checkFormValidated()
                .checkModalIsNotVisible();
    }

    @Test
    void negativeTestRegistrationFormWithIncorrectNumber() {
        automationPracticeFormPage
                .openPage(URL_PRACTICE_FORM)
                .typeFirstName(FIRST_NAME)
                .typeLastName(LAST_NAME)
                .selectGender(GENDER)
                .typeUserNumber(PHONE_INCORRECT)
                .setDateOfBirth(DAY, MONTH, YEAR)
                .submitForm()
                .checkFormValidated()
                .checkModalIsNotVisible();
    }

    @Test
    void negativeTestRegistrationFormWithoutLastName() {
        automationPracticeFormPage
                .openPage(URL_PRACTICE_FORM)
                .typeFirstName(FIRST_NAME)
                .selectGender(GENDER)
                .typeUserNumber(PHONE)
                .setDateOfBirth(DAY, MONTH, YEAR)
                .submitForm()
                .checkFormValidated()
                .checkModalIsNotVisible();
    }
}
