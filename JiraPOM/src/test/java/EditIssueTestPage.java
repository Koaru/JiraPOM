import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;
public class EditIssueTestPage {
    static WebDriver driver;
    static LoginPage loginPage;
    static IssuePage issuePage;
    static DashboardPage dashboardPage;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");
    static final String SUMMARY_DATA = util.ReadFromConfig.readFromFile("SUMMARY");
    static final String EDIT_DATA = util.ReadFromConfig.readFromFile("EDIT_ISSUE_SUMMARY");
    @BeforeEach
    public void init(){

        loginPage = new LoginPage();
        issuePage = new IssuePage();
        dashboardPage = new DashboardPage();
        loginPage.navigateToDashboardLoginPage();
        loginPage.loggingInInDashboard(VALID_USERNAME,VALID_PASSWORD);
    }

    public void editIssue(String project){
        dashboardPage.clickOnCreateBtn();
        dashboardPage.modifyProjectField(project);
        dashboardPage.fillSummary(SUMMARY_DATA);
        dashboardPage.clickOnCreateIssueBtn();
        dashboardPage.clickOnCreatedIssueLink();
        issuePage.clickOnEditIssueBtn();
        issuePage.editSummary(EDIT_DATA);
        issuePage.clickOnUpdateBtn();
        System.out.println(issuePage.getSummary());
        Assertions.assertEquals(issuePage.getSummary(),EDIT_DATA);
        System.out.println(issuePage.getSummary());
        issuePage.clickOnDeleteBtn();
    }

    public void creatingIssue(){
        dashboardPage.clickOnCreateBtn();
        dashboardPage.fillSummary(SUMMARY_DATA);
        dashboardPage.clickOnCreateIssueBtn();
        dashboardPage.clickOnCreatedIssueLink();
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/project.csv")
    public void editIssueParameterized(String input){
        editIssue(input);
    }

    @Test
    public void editIssueWithEmptySummary(){
        creatingIssue();
        issuePage.clickOnEditIssueBtn();
        issuePage.editSummary("");
        issuePage.clickOnUpdateBtn();
        Assertions.assertTrue(issuePage.editIssueErrorIsPresent());
        issuePage.clickOnCancelButtonAndAcceptAlert();
        issuePage.clickOnDeleteBtn();
    }
    @Test
    public void cancelEditIssue(){
        creatingIssue();
        issuePage.clickOnEditIssueBtn();
        issuePage.clickOnCancelBtn();
        Assertions.assertEquals(issuePage.getSummary(),SUMMARY_DATA);
        issuePage.clickOnDeleteBtn();
    }

    @Test
    public void cancelEditIssueAfterAlteringData(){
        creatingIssue();
        issuePage.clickOnEditIssueBtn();
        issuePage.editSummary("edit");
        issuePage.clickOnCancelButtonAndAcceptAlert();
        Assertions.assertEquals(issuePage.getSummary(),SUMMARY_DATA);
        issuePage.clickOnDeleteBtn();
    }

   @AfterEach
    public void tearDown(){
        loginPage.quit();
    }
}
