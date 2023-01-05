import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateIssueTest {
    static WebDriver driver;
    static Login login;
    static Dashboard dashboard;
    static Issue issue;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");
    static final String SUMMARY_DATA = util.ReadFromConfig.readFromFile("SUMMARY");
    static final String NON_EXISTING_PROJECT = util.ReadFromConfig.readFromFile("NON_EXISTING_PROJECT");
    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        login = new Login(driver);
        issue = new Issue(driver);
        dashboard = new Dashboard(driver);
        driver.get(URL);
        driver.manage().window().maximize();
        login.loggingInInDashboard(VALID_USERNAME,VALID_PASSWORD);
    }

    @Test
    @Order(1)
    public void createIssueHappy(){
        dashboard.clickOnCreateBtn();
        dashboard.fillSummary(SUMMARY_DATA);
        dashboard.clickOnCreateIssueBtn();
        String actualResult = dashboard.getIssueText();
        dashboard.clickOnCreatedIssueLink();
        String expectedResult = issue.getIssueKey() + " - " + SUMMARY_DATA;
        Assertions.assertEquals(actualResult,expectedResult);
        issue.clickOnDeleteBtn();
        Assertions.assertTrue(dashboard.isDeleteIssueValidate());
    }

    @Test
    @Order(2)
    public void createIssueCancel(){
        dashboard.clickOnCreateBtn();
        Boolean isActive = dashboard.isModalWindowsDisplayed();
        dashboard.clickOnCancelBtn();
        isActive = !isActive;
        Assertions.assertFalse(isActive);
    }

    @Test
    @Order(3)
    public void createIssueWithEmptySummaryField(){
        dashboard.clickOnCreateBtn();
        dashboard.clickOnCreateIssueBtn();
        Assertions.assertTrue(dashboard.isSummaryFieldEmpty());
    }

    @Test
    @Order(4)
    public void createIssueWithNonExistingProject(){
        dashboard.clickOnCreateBtn();
        String beforeText = dashboard.getProjectFieldText();
        dashboard.modifyProjectField(NON_EXISTING_PROJECT);
        String afterText = dashboard.getProjectFieldText();
        Assertions.assertEquals(beforeText,afterText);
    }

    public void createIssueByProjectAndType(String project, String type){
        dashboard.clickOnCreateBtn();
        dashboard.modifyProjectField(project);
        dashboard.modifyTypeField(type);
        dashboard.fillSummary(SUMMARY_DATA);
        dashboard.clickOnCreateIssueBtn();
        String actualResult = dashboard.getIssueText();
        dashboard.clickOnCreatedIssueLink();
        String expectedResult = issue.getIssueKey() + " - " + SUMMARY_DATA;
        Assertions.assertEquals(actualResult,expectedResult);
        issue.clickOnDeleteBtn();
        Assertions.assertTrue(dashboard.isDeleteIssueValidate());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createissue.csv")
    public void createIssueParameterized(String project,String type){
        createIssueByProjectAndType(project,type);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}