import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;

public class DashboardLoginTest {
    static WebDriver driver;
    static Login login;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");

    final String INVALID_USERNAME = "invalid-username";
    final String INVALID_PASSWORD = "invalid-password";
    final String EXPECTED_URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";

    @BeforeAll
    public static void setup(){
        driver = new ChromeDriver();
        login = new Login(driver);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test
    public void validLogin(){
        Dashboard dashboard = new Dashboard(driver);
        Profile profile = new Profile(driver);
        login.setUsername(VALID_USERNAME);
        login.setPassword(VALID_PASSWORD);
        login.clickOnDashboardLoginBtn();
        dashboard.clickOnProfileBtn();
        dashboard.clickOnProfile();
        Assertions.assertEquals(VALID_USERNAME,profile.getUsername());
    }

    @Test
    public void invalidLoginWithInvalidUsername(){
        login.setUsername(INVALID_USERNAME);
        login.setPassword(VALID_PASSWORD);
        login.clickOnDashboardLoginBtn();
        Assertions.assertTrue(login.usernameErrorIsPresent());
    }

    @Test
    public void invalidLoginWithInvalidPassword(){
        login.setUsername(VALID_USERNAME);
        login.setPassword(INVALID_PASSWORD);
        login.clickOnDashboardLoginBtn();
        Assertions.assertTrue(login.usernameErrorIsPresent());
    }
}
