package helpers;

public class TestProperties {

    public static final String baseUrl =
            System.getProperty("baseUrl", "https://demoqa.com");
    public static final String remoteUrl =
            System.getProperty("remoteUrl", "");
    public static final String remoteCredentials =
            System.getProperty("remoteCredentials", "");
    public static final String browser =
            System.getProperty("browser", "chrome");
    public static final String browserVersion =
            System.getProperty("browserVersion", "157.0");
    public static final String browserSize =
            System.getProperty("browserSize", "1920x1080");
    public static final boolean headless =
            Boolean.parseBoolean(System.getProperty("headless", "false"));
}
