import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;
public class BrowseIssueTest {
    static WebDriver driver;
    static Login login;
    static Issue issue;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");
    static final String MTP_ISSUE = util.ReadFromConfig.readFromFile("MTP_BROWSE_ISSUE");
    static final String COALA_ISSUE = util.ReadFromConfig.readFromFile("COALA_BROWSE_ISSUE");
    static final String TOUCAN_ISSUE = util.ReadFromConfig.readFromFile("TOUCAN_BROWSE_ISSUE");
    static final String JETI_ISSUE = util.ReadFromConfig.readFromFile("JETI_BROWSE_ISSUE");
    static final String MTP_EXPECTED = util.ReadFromConfig.readFromFile("MTP_BROWSE_ISSUE_EXPECTED");
    static final String COALA_EXPECTED = util.ReadFromConfig.readFromFile("COALA_BROWSE_ISSUE_EXPECTED");
    static final String TOUCAN_EXPECTED = util.ReadFromConfig.readFromFile("TOUCAN_BROWSE_ISSUE_EXPECTED");
    static final String JETI_EXPECTED = util.ReadFromConfig.readFromFile("JETI_BROWSE_ISSUE_EXPECTED");

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        login = new Login(driver);
        issue = new Issue(driver);
        driver.get(URL);
        driver.manage().window().maximize();
        login.loggingInInDashboard(VALID_USERNAME,VALID_PASSWORD);
    }

    public void browseIssue(String issueUrl, String expected){
        driver.get(issueUrl);
        Assertions.assertEquals(issue.getIssueKey(),expected);
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
