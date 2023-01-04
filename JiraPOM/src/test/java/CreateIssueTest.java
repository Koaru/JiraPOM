import com.beust.jcommander.Parameter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;

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
    static final String COALA = util.ReadFromConfig.readFromFile("COALA");
    static final String MTP = util.ReadFromConfig.readFromFile("MTP");
    static final String JETI = util.ReadFromConfig.readFromFile("JETI");
    static final String TOUCAN = util.ReadFromConfig.readFromFile("TOUCAN");
    static final String BUG = util.ReadFromConfig.readFromFile("BUG");
    static final String TASK = util.ReadFromConfig.readFromFile("TASK");
    static final String STORY = util.ReadFromConfig.readFromFile("STROY");


    @BeforeAll
    public static void setup(){
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

    @Test
    @Order(5)
    public void createIssueCoalaAndBug(){
        createIssueByProjectAndType(COALA,BUG);
    }

    @Test
    @Order(6)
    public void createIssueCoalaAndStory(){
        createIssueByProjectAndType(COALA,STORY);
    }

    @Test
    @Order(7)
    public void createIssueCoalaAndTask(){
        createIssueByProjectAndType(COALA,TASK);
    }

    @Test
    @Order(8)
    public void createIssueToucanAndBug(){
        createIssueByProjectAndType(TOUCAN,BUG);
    }
    @Test
    @Order(9)
    public void createIssueToucanAndStory(){
        createIssueByProjectAndType(TOUCAN,STORY);
    }
    @Test
    @Order(10)
    public void createIssueToucanAndTask(){
        createIssueByProjectAndType(TOUCAN,TASK);
    }
    @Test
    @Order(11)
    public void createIssueJetiAndBug(){
        createIssueByProjectAndType(JETI,BUG);
    }
    @Test
    @Order(12)
    public void createIssueJetiAndStory(){
        createIssueByProjectAndType(JETI,STORY);
    }
    @Test
    @Order(13)
    public void createIssueJetiAndTask(){
        createIssueByProjectAndType(JETI,TASK);
    }

    @AfterAll
    @Order(14)
    public static void tearDown(){
        driver.close();
    }
}