import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pageFactory.*;
public class BrowseIssueTestPage {
    static LoginPage loginPage;
    static IssuePage issuePage;
    static final String VALID_USERNAME = System.getProperty("username");
    static final String VALID_PASSWORD = System.getProperty("password");

    @BeforeEach
    public void init(){
        loginPage = new LoginPage();
        issuePage = new IssuePage();
        loginPage.navigateToLoginPage();
        loginPage.loggingIn(VALID_USERNAME,VALID_PASSWORD);
    }

    public void browseIssue(String issueUrl, String expected){
        loginPage.navigate(issueUrl);
        Assertions.assertEquals(issuePage.getIssueKey(),expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/issues.csv")
    public void browseIssueParameterized(String issue,String expected){
        browseIssue(issue,expected);
    }

    @AfterEach
    public void tearDown(){
        loginPage.quit();
    }

}
