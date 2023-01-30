import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;
public class BrowseIssueTestPage {
    static WebDriver driver;
    static LoginPage loginPage;
    static IssuePage issuePage;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");

    @BeforeEach
    public void init(){
        loginPage = new LoginPage();
        issuePage = new IssuePage();
        driver.get(URL);
        driver.manage().window().maximize();
        loginPage.loggingInInDashboard(VALID_USERNAME,VALID_PASSWORD);
    }

    public void browseIssue(String issueUrl, String expected){
        driver.get(issueUrl);
        Assertions.assertEquals(issuePage.getIssueKey(),expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/issues.csv")
    public void browseIssueParameterized(String issue,String expected){
        browseIssue(issue,expected);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
