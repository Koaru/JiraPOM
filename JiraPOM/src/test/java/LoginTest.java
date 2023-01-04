import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;
public class LoginTest {
    static WebDriver driver;
    static Login login;
    static final String URL = "https://jira-auto.codecool.metastage.net/login.jsp";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");

    final String INVALID_USERNAME = "invalid-username";
    final String INVALID_PASSWORD = "invalid-password";
    @BeforeAll
    public static void setup(){
        driver = new ChromeDriver();
        login = new Login(driver);
    }
    @BeforeEach
    public void init(){
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test
    public void validLogin(){
        Dashboard dashboard = new Dashboard(driver);
        Profile profile = new Profile(driver);
        login.loggingIn(VALID_USERNAME,VALID_PASSWORD);
        dashboard.clickOnProfileBtn();
        dashboard.clickOnProfile();
        Assertions.assertEquals(VALID_USERNAME,profile.getUsername());
    }

    @Test
    public void invalidLoginWithInvalidUsername(){
        login.loggingIn(INVALID_USERNAME,VALID_PASSWORD);
        Assertions.assertTrue(login.usernameErrorIsPresent());
    }

    @Test
    public void invalidLoginWithInvalidPassword(){
        login.loggingIn(VALID_USERNAME,INVALID_PASSWORD);
        Assertions.assertTrue(login.usernameErrorIsPresent());
    }

    @AfterEach
    public void tearDown(){
        driver.close();
    }

    @AfterAll
    public static void quit(){
        driver.quit();
    }
}
