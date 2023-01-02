import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;

public class LoginTest {
    static WebDriver driver;
    static Login login;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    final String VALID_USERNAME = System.getenv("VALID_USERNAME");
    final String VALID_PASSWORD = System.getenv("VALID_PASSWORD");
    final String INVALID_USERNAME = "invalid-username";
    final String INVALID_PASSWORD = "invalid-password";

    @BeforeAll
    public static void setup(){
        driver = new ChromeDriver();
        login = new Login(driver);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test
    public void validLogin(){
        login.setUsername(VALID_USERNAME);
        login.setPassword(VALID_PASSWORD);
        login.clickOnLoginBtn();
    }

    @Test
    public void invalidLoginWithInvalidUsername(){
        login.setUsername(INVALID_USERNAME);
        login.setPassword(VALID_PASSWORD);
        login.clickOnLoginBtn();
    }

    @Test
    public void invalidLoginWithInvalidPassword(){
        login.setUsername(VALID_USERNAME);
        login.setPassword(INVALID_PASSWORD);
        login.clickOnLoginBtn();
    }
}
