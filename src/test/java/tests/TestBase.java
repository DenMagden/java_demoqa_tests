package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    public static final String baseUrl =
            System.getProperty("baseUrl", "https://demoqa.com");
    public static final String remoteUrl =
            System.getProperty("remoteUrl", "");
    public static final String remoteCredentials =
            System.getProperty("remoteCredentials", "");
    public static final String browser =
            System.getProperty("browser", "chrome");
    public static final String browserVersion =
            System.getProperty("browserVersion", "");
    public static final String browserSize =
            System.getProperty("browserSize", "1920x1080");
    public static final boolean headless =
            Boolean.parseBoolean(System.getProperty("headless", "false"));

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(true));
    }

    @BeforeAll
    static void configurationBrowser() {
        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
        Configuration.baseUrl = baseUrl;
        Configuration.browserVersion = browserVersion.isBlank() ? null : browserVersion;
        Configuration.headless = headless;
        Configuration.remote = null;

        if (remoteUrl != null && !remoteUrl.isBlank()) {
            Configuration.remote = remoteCredentials.isBlank()
                    ? "https://" + remoteUrl
                    : "https://" + remoteCredentials + "@" + remoteUrl;
        }

        switch (browser) {
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();

                options.addArguments(
                        "--disable-dev-shm-usage",
                        "--no-sandbox"
                );

                Configuration.browserCapabilities = options;
            }

            case "firefox" -> {
                FirefoxOptions options = new FirefoxOptions();

                Configuration.browserCapabilities = options;
            }

            default -> throw new IllegalArgumentException(
                    "Unsupported browser: " + browser
            );
        }

        if (Configuration.remote != null) {
            Configuration.browserCapabilities.setCapability(
                    "selenoid:options",
                    Map.of(
                            "enableVNC", true,
                            "enableVideo", true
                    )
            );
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.addVideo();
        closeWebDriver();
    }
}
