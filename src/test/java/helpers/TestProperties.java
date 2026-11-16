package helpers;

import org.junit.jupiter.api.Test;

public class TestProperties {

    public static final String baseUrl =
            System.getProperty("baseUrl", "https://demoqa.com");
    public static final String remoteUrl =
            System.getProperty("remoteUrl", "selenoid.qa.guru/wd/hub");
    public static final String remoteCredentials =
            System.getProperty("remoteCredentials", "user1:1234");
    public static final String browser =
            System.getProperty("browser", "chrome");
    public static final String browserVersion =
            System.getProperty("browserVersion", "157.0");
    public static final String browserSize =
            System.getProperty("browserSize", "1920x1080");
    public static final boolean headless =
            Boolean.parseBoolean(System.getProperty("headless", "false"));

    @Test
    void propertyBaseUrlTest() {
        System.out.println("Base URL is: " + baseUrl);
    }

    @Test
    void propertyRemoteTest() {
        System.out.println("Remote browser URL is: " + "https://" + remoteCredentials + "@" + remoteUrl);
    }

    @Test
    void propertyBrowserTest() {
        System.out.println("Browser is: " + browser);
    }

    @Test
    void propertyBrowserVersionTest() {
        System.out.println("Browser version is: " + browserVersion);
    }

    @Test
    void propertyBrowserSizeTest() {
        System.out.println("Browser size is: " + browserSize);
    }

    @Test
    void propertyHeadlessTest() {
        System.out.println("Headless is: " + headless);
    }
}
