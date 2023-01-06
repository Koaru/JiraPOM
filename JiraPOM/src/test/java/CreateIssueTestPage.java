import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateIssueTestPage {
    static WebDriver driver;
    static LoginPage loginPage;
    static DashboardPage dashboardPage;
    static IssuePage issuePage;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");
    static final String SUMMARY_DATA = util.ReadFromConfig.readFromFile("SUMMARY");
    static final String NON_EXISTING_PROJECT = util.ReadFromConfig.readFromFile("NON_EXISTING_PROJECT");
    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        issuePage = new IssuePage(driver);
        dashboardPage = new DashboardPage(driver);
        driver.get(URL);
        driver.manage().window().maximize();
        loginPage.loggingInInDashboard(VALID_USERNAME,VALID_PASSWORD);
    }

    @Test
    @Order(1)
    public void createIssueHappy(){
        dashboardPage.clickOnCreateBtn();
        dashboardPage.fillSummary(SUMMARY_DATA);
        dashboardPage.clickOnCreateIssueBtn();
        String actualResult = dashboardPage.getIssueText();
        dashboardPage.clickOnCreatedIssueLink();
        String expectedResult = issuePage.getIssueKey() + " - " + SUMMARY_DATA;
        Assertions.assertEquals(actualResult,expectedResult);
        issuePage.clickOnDeleteBtn();
        Assertions.assertTrue(dashboardPage.isDeleteIssueValidate());
    }

    @Test
    @Order(2)
    public void createIssueCancel(){
        dashboardPage.clickOnCreateBtn();
        Boolean isActive = dashboardPage.isModalWindowsDisplayed();
        dashboardPage.clickOnCancelBtn();
        isActive = !isActive;
        Assertions.assertFalse(isActive);
    }

    @Test
    @Order(3)
    public void createIssueWithEmptySummaryField(){
        dashboardPage.clickOnCreateBtn();
        dashboardPage.clickOnCreateIssueBtn();
        Assertions.assertTrue(dashboardPage.isSummaryFieldEmpty());
    }

    @Test
    @Order(4)
    public void createIssueWithNonExistingProject(){
        dashboardPage.clickOnCreateBtn();
        String beforeText = dashboardPage.getProjectFieldText();
        dashboardPage.modifyProjectField(NON_EXISTING_PROJECT);
        String afterText = dashboardPage.getProjectFieldText();
        Assertions.assertEquals(beforeText,afterText);
    }

    public void createIssueByProjectAndType(String project, String type){
        dashboardPage.clickOnCreateBtn();
        dashboardPage.modifyProjectField(project);
        dashboardPage.modifyTypeField(type);
        dashboardPage.fillSummary(SUMMARY_DATA);
        dashboardPage.clickOnCreateIssueBtn();
        String actualResult = dashboardPage.getIssueText();
        dashboardPage.clickOnCreatedIssueLink();
        String expectedResult = issuePage.getIssueKey() + " - " + SUMMARY_DATA;
        Assertions.assertEquals(actualResult,expectedResult);
        issuePage.clickOnDeleteBtn();
        Assertions.assertTrue(dashboardPage.isDeleteIssueValidate());
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