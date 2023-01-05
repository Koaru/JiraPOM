import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;
public class EditIssueTest {
    static WebDriver driver;
    static Login login;
    static Issue issue;
    static Dashboard dashboard;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");
    static final String SUMMARY_DATA = util.ReadFromConfig.readFromFile("SUMMARY");
    static final String EDIT_DATA = util.ReadFromConfig.readFromFile("EDIT_ISSUE_SUMMARY");
    static final String COALA = util.ReadFromConfig.readFromFile("COALA");
    static final String MTP = util.ReadFromConfig.readFromFile("MTP");
    static final String JETI = util.ReadFromConfig.readFromFile("JETI");
    static final String TOUCAN = util.ReadFromConfig.readFromFile("TOUCAN");
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

    public void editIssue(String project){
        dashboard.clickOnCreateBtn();
        dashboard.modifyProjectField(project);
        dashboard.fillSummary(SUMMARY_DATA);
        dashboard.clickOnCreateIssueBtn();
        dashboard.clickOnCreatedIssueLink();
        issue.clickOnEditIssueBtn();
        issue.editSummary(EDIT_DATA);
        issue.clickOnUpdateBtn();
        System.out.println(issue.getSummary());
        Assertions.assertEquals(issue.getSummary(),EDIT_DATA);
        System.out.println(issue.getSummary());
        issue.clickOnDeleteBtn();
    }

    public void creatingIssue(){
        dashboard.clickOnCreateBtn();
        dashboard.fillSummary(SUMMARY_DATA);
        dashboard.clickOnCreateIssueBtn();
        dashboard.clickOnCreatedIssueLink();
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/project.csv")
    public void editIssueParameterized(String input){
        editIssue(input);
    }

    @Test
    public void editIssueHappy(){
        editIssue(MTP);
    }

    @Test
    public void editIssueCoala(){
        editIssue(COALA);
    }

    @Test
    public void editIssueToucan(){
        editIssue(TOUCAN);
    }

    @Test
    public void editIssueJeti(){
        editIssue(JETI);
    }

    @Test
    public void editIssueWithEmptySummary(){
        creatingIssue();
        issue.clickOnEditIssueBtn();
        issue.editSummary("");
        issue.clickOnUpdateBtn();
        Assertions.assertTrue(issue.editIssueErrorIsPresent());
        issue.clickOnCancelButtonAndAcceptAlert();
        issue.clickOnDeleteBtn();
    }
    @Test
    public void cancelEditIssue(){
        creatingIssue();
        issue.clickOnEditIssueBtn();
        issue.clickOnCancelBtn();
        Assertions.assertEquals(issue.getSummary(),SUMMARY_DATA);
        issue.clickOnDeleteBtn();
    }

    @Test
    public void cancelEditIssueAfterAlteringData(){
        creatingIssue();
        issue.clickOnEditIssueBtn();
        issue.editSummary("edit");
        issue.clickOnCancelButtonAndAcceptAlert();
        Assertions.assertEquals(issue.getSummary(),SUMMARY_DATA);
        issue.clickOnDeleteBtn();
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
