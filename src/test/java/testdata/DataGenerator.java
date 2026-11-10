package testdata;

import net.datafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataGenerator {

    private final Faker faker = new Faker();
    private final Faker fakerRu = new Faker(new Locale("ru"));

    public String generateUserName() {
        return fakerRu.name().fullName();
    }

    public String generateEmail() {
        return faker.internet().emailAddress();
    }

    public String generateCurrentAddress() {
        return fakerRu.address().fullAddress();
    }

    public String generatePermanentAddress() {
        return fakerRu.address().streetAddress();
    }

    public String generateIncorrectEmail() {
        return faker.name() + "@";
    }

    public String generateFirstName() {
        return fakerRu.name().firstName();
    }

    public String generateLastName() {
        return fakerRu.name().lastName();
    }

    public String generatePhone() {
        return faker.number().digits(10);
    }

    public String generatePhoneIncomplete() {
        return faker.number().digits(9);
    }

    public String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public String getRandomSubject() {
        return faker.options().option(
                "Accounting", "Arts", "Biology", "Chemistry", "Civics",
                "Commerce", "Computer Science", "Economics", "English",
                "Hindi", "History", "Maths", "Physics", "Social Studies"
        );
    }

    public List<String> getRandomHobbies() {
        return new ArrayList<>(faker.options().subset(2, "Sports", "Reading", "Music"));
    }

    public String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> throw new IllegalArgumentException("Неизвестный штат: " + state);
        };
    }

    public LocalDate generateBirthDate() {
        return faker.timeAndDate().birthday();
    }

    public String getRandomImage() {
        return faker.options().option("img/qaguru.png",
                "img/textBox.jpg", "img/toolsQA.png");
    }
}
