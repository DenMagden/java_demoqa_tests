package testdata;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DataGenerator {

    private static final Faker faker = new Faker();
    private static final Faker fakerRu = new Faker(new Locale("ru"));

    public static String generateUserName() {
        return fakerRu.name().fullName();
    }

    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    public static String generateCurrentAddress() {
        return fakerRu.address().fullAddress();
    }

    public static String generatePermanentAddress() {
        return fakerRu.address().streetAddress();
    }

    public static String generateIncorrectEmail() {
        return faker.name() + "@";
    }

    public static String generateFirstName() {
        return fakerRu.name().firstName();
    }

    public static String generateLastName() {
        return fakerRu.name().lastName();
    }

    public static String generatePhone() {
        return faker.number().digits(10);
    }

    public static String generatePhoneIncomplete() {
        return faker.number().digits(9);
    }

    public static String generateGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    private static final String[] SUBJECTS = {
            "Accounting", "Arts", "Biology", "Chemistry", "Civics",
            "Commerce", "Computer Science", "Economics", "English",
            "Hindi", "History", "Maths", "Physics", "Social Studies"
    };

    public static String generateSubject() {
        return faker.options().option(SUBJECTS);
    }

    public static List<String> generateHobbies() {
        List<String> hobbies = new ArrayList<>(
                List.of("Sports", "Reading", "Music")
        );

        Collections.shuffle(hobbies);

        return hobbies.subList(0, 2);
    }

    private static final Map<String, List<String>> STATE_CITIES = Map.of(
            "NCR", List.of("Delhi", "Gurgaon", "Noida"),
            "Uttar Pradesh", List.of("Agra", "Lucknow", "Merrut"),
            "Haryana", List.of("Karnal", "Panipat"),
            "Rajasthan", List.of("Jaipur", "Jaiselmer")
    );

    public static StateCity generateStateCity() {
        String state = faker.options().option(
                STATE_CITIES.keySet().toArray(new String[0])
        );

        List<String> cities = STATE_CITIES.get(state);

        String city = faker.options().option(
                cities.toArray(new String[0])
        );

        return new StateCity(state, city);
    }

    public static LocalDate getRandomBirthDate() {
        return faker.timeAndDate().birthday();
    }

    public static String getRandomImage() {
        return faker.options().option("img/qaguru.png",
                "img/textBox.jpg", "img/toolsQA.png");
    }
}
