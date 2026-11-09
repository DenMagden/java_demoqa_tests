package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testdata.DataGenerator;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static testdata.TestData.URL_PRACTICE_FORM;
import static utils.RandomUtils.getRandomString;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AutomationPracticeFormTests extends TestBase {

    private final DataGenerator dataGenerator = new DataGenerator();

    private String firstName;
    private String lastName;
    private String userEmail;
    private String phone;
    private String phoneIncomplete;
    private String phoneIncorrect;
    private String picture;
    private String currentAddress;
    private String gender;
    private LocalDate birthDate;
    private String subject;
    private List<String> hobbies;
    private String state;
    private String city;

    @BeforeEach
    void prepareRandomData() {
        firstName = dataGenerator.generateFirstName();
        lastName = dataGenerator.generateLastName();
        userEmail = dataGenerator.generateEmail();
        phone = dataGenerator.generatePhone();
        phoneIncomplete = dataGenerator.generatePhoneIncomplete();
        phoneIncorrect = getRandomString(10);
        picture = dataGenerator.getRandomImage();
        currentAddress = dataGenerator.generateCurrentAddress();
        gender = dataGenerator.getRandomGender();
        birthDate = dataGenerator.generateBirthDate();
        subject = dataGenerator.getRandomSubject();
        hobbies = dataGenerator.getRandomHobbies();
        state = dataGenerator.getRandomState();
        city = dataGenerator.getRandomCity(state);
    }

    @DisplayName("Проверка всех полей формы регистрации")
    @Test
    void successfulTestRegistrationFormAllFields() {
        automationPracticeFormPage
                .openPage(URL_PRACTICE_FORM)
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .selectGender(gender)
                .typeUserNumber(phone)
                .setDateOfBirth(birthDate)
                .selectSubject(subject)
                .setHobbies(hobbies.get(0))
                .setHobbies(hobbies.get(1))
                .uploadPicture(picture)
                .currentAddressTextArea(currentAddress)
                .selectState(state)
                .selectCity(city)
                .submitForm()
                .checkModalField("Student Name", firstName + " " + lastName)
                .checkModalField("Student Email", userEmail)
                .checkModalField("Gender", gender)
                .checkModalField("Mobile", phone)
                .checkModalField("Date of Birth", birthDate.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH)))
                .checkModalField("Subjects", subject)
                .checkModalField("Hobbies", String.join(", ", hobbies))
                .checkModalField("Picture", Path.of(picture).getFileName().toString())
                .checkModalField("Address", currentAddress)
                .checkModalField("State and City", state + " " + city);
    }

    @DisplayName("Проверка обязательных полей формы регистрации")
    @Test
    void successfulTestRegistrationFormRequiredFields() {
        automationPracticeFormPage
                .openPage(URL_PRACTICE_FORM)
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .selectGender(gender)
                .typeUserNumber(phone)
                .setDateOfBirth(birthDate)
                .submitForm()
                .checkModalField("Student Name", firstName + " " + lastName)
                .checkModalField("Student Email", "")
                .checkModalField("Gender", gender)
                .checkModalField("Mobile", phone)
                .checkModalField("Date of Birth", birthDate.format(DateTimeFormatter.ofPattern("dd MMMM,yyyy", Locale.ENGLISH)))
                .checkModalField("Subjects", "")
                .checkModalField("Hobbies", "")
                .checkModalField("Picture", "")
                .checkModalField("Address", "")
                .checkModalField("State and City", "");
    }

    Stream<Arguments> negativeTestsRegistrationForm() {
        return Stream.of(
                Arguments.of("имя не указано", null, lastName, phone),
                Arguments.of("фамилия не указана", firstName, null, phone),
                Arguments.of("номер не полный", firstName, lastName, phoneIncomplete),
                Arguments.of("номер не корректный", firstName, lastName, phoneIncorrect)
        );
    }

    @ParameterizedTest(name = "Проверка формы регистрации, если {0}")
    @MethodSource("negativeTestsRegistrationForm")
    void negativeTestsRegistrationForm(String scenario, String firstName, String lastName, String phone) {
        automationPracticeFormPage
                .openPage(URL_PRACTICE_FORM)
                .typeLastName(firstName)
                .typeLastName(lastName)
                .selectGender(gender)
                .typeUserNumber(phone)
                .submitForm()
                .checkFormValidated()
                .checkModalIsNotVisible();
    }
}
