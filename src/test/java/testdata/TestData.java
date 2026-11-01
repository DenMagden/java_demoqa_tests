package testdata;

import net.datafaker.Faker;

import java.util.Locale;

public class TestData {

    public static String URL_TEXT_BOX = "/text-box";
    public static String URL_PRACTICE_FORM = "/automation-practice-form";

    public static String GENDER = faker.options().option("Male", "Female", "Other");
//    public static String USER_EMAIL_INCORRECT = ".c";
    public static String USER_EMAIL_INCORRECT = faker.name() + "@";
    public static String CURRENT_ADDRESS = "Новосибирск, ул. Ленина д. 22";
    public static String PERMANENT_ADDRESS = "Nsk";
    public static String PHONE = "7999333221";
    public static String PHONE_INCOMPLETE = "799933322";
    public static String PHONE_INCORRECT = "qawdrgdcbd";
    public static String YEAR = "2020";
    public static String MONTH = "June";
    public static String DAY = "15";
    public static String SUBJECT = "English";
    public static String HOBBY_1 = "Sports";
    public static String HOBBY_2 = "Reading";
    public static String PICTURE = "qaguru.png";
    public static String CITY_1 = "NCR";
    public static String CITY_2 = "Gurgaon";
}
