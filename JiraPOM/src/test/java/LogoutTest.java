import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;
public class LogoutTest {
    static WebDriver driver;
    static LoginPage loginPage;
    static final String URL = "https://jira-auto.codecool.metastage.net/login.jsp";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");
    final String EXPECTED_MESSAGE = "You are now logged out. Any automatic login has also been stopped.\n" +
            "Didn't mean to log out? Log in again.";
    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get(URL);
        driver.manage().window().maximize();
        loginPage.loggingIn(VALID_USERNAME,VALID_PASSWORD);
    }

    @Test
    public void logout(){
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickOnProfileBtn();
        dashboardPage.clickOnLogoutBtn();
        Assertions.assertEquals(dashboardPage.logoutMessage(),EXPECTED_MESSAGE);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
