import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;

public class DashboardPageLoginTestPage {
    static WebDriver driver;
    static LoginPage loginPage;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");

    final String INVALID_USERNAME = "invalid-username";
    final String INVALID_PASSWORD = "invalid-password";
    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test
    public void validLogin(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);
        loginPage.loggingInInDashboard(VALID_USERNAME,VALID_PASSWORD);
        dashboardPage.clickOnProfileBtn();
        dashboardPage.clickOnProfile();
        Assertions.assertEquals(VALID_USERNAME, profilePage.getUsername());
    }

    @Test
    public void invalidLoginWithInvalidUsername(){
        loginPage.loggingInInDashboard(INVALID_USERNAME,VALID_PASSWORD);
        Assertions.assertTrue(loginPage.usernameErrorIsPresent());
    }

    @Test
    public void invalidLoginWithInvalidPassword(){
        loginPage.loggingInInDashboard(VALID_USERNAME,INVALID_PASSWORD);
        Assertions.assertTrue(loginPage.usernameErrorIsPresent());
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
