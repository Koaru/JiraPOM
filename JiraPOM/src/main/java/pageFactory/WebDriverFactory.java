package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class WebDriverFactory {
    static String gridPassword = System.getProperty("password");
    static String gridUrl = String.format("https://selenium:%s@seleniumhub.codecool.metastage.net/wd/hub",gridPassword);
    private static WebDriver webDriver = null;
    public static WebDriver createWebDriver(Boolean isRemote, String browserType) throws MalformedURLException {
        if (webDriver == null) {
            if (!isRemote) {
                switch (browserType) {
                    case "firefox":
                        webDriver = new FirefoxDriver();
                        break;
                    case "chrome":
                    default:
                        webDriver = new ChromeDriver();
                        break;
                }
            } else {
                if (Objects.equals(browserType, "chrome")){
                ChromeOptions chromeOptions = new ChromeOptions();
                webDriver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                }
                else {
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    webDriver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                }
            }
        }
        return webDriver;
    }

    public static void shutDown() {
        webDriver.quit();
        webDriver = null;
    }
}
