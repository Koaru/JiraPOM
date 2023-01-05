
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;

public class BrowseProjectTest {

    static WebDriver driver;
    static Login login;
    static Project project;

    static Dashboard dashboard;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");

    static final String COALA_PROJECT_URL = util.ReadFromConfig.readFromFile("COALA_BROWSE_PROJECT");
    static final String TOUCAN_PROJECT_URL = util.ReadFromConfig.readFromFile("TOUCAN_BROWSE_PROJECT");
    static final String JETI_PROJECT_URL = util.ReadFromConfig.readFromFile("JETI_BROWSE_PROJECT");
    static final String MTP_PROJECT_URL = util.ReadFromConfig.readFromFile("MTP_BROWSE_PROJECT");
    static final String COALA_KEY = util.ReadFromConfig.readFromFile("COALA_BROWSE_PROJECT_EXPECTED");
    static final String TOUCAN_KEY = util.ReadFromConfig.readFromFile("TOUCAN_BROWSE_PROJECT_EXPECTED");
    static final String JETI_KEY = util.ReadFromConfig.readFromFile("JETI_BROWSE_PROJECT_EXPECTED");
    static final String MTP_KEY = util.ReadFromConfig.readFromFile("MTP_BROWSE_PROJECT_EXPECTED");
    static final String NOT_EXISTING_PROJECT = util.ReadFromConfig.readFromFile("NOT_EXISTING_PROJECT");

    @BeforeEach
    public void init() {
        driver = new ChromeDriver();
        login = new Login(driver);
        project = new Project(driver);
        dashboard = new Dashboard(driver);
        driver.get(URL);
        driver.manage().window().maximize();
        login.loggingInInDashboard(VALID_USERNAME, VALID_PASSWORD);
    }


    public void browseProject(String expected, String URL) {
        //System.out.println(dashboard.isPageLoaded());
        if (dashboard.isPageLoaded()) {
            driver.get(URL);
        }
        Assertions.assertEquals(expected, project.getKey());
    }

    @Test
    public void browseProjectMTP() {
        browseProject(MTP_KEY, MTP_PROJECT_URL);

    }

    @Test
    public void browseProjectCOALA() {
        browseProject(COALA_KEY, COALA_PROJECT_URL);
    }

    @Test
    public void browseProjectTOUCAN() {
        browseProject(TOUCAN_KEY, TOUCAN_PROJECT_URL);
    }

    @Test
    public void browseProjectJETI() {
        browseProject(JETI_KEY, JETI_PROJECT_URL);
    }

    @Test
    public void browseNonExistingProject() {
        if (dashboard.isPageLoaded()) {
            driver.get(NOT_EXISTING_PROJECT);
        }
        String errorMessage = project.getErrorMessage();
        Assertions.assertEquals("You can't view this project",errorMessage);

    }

    @Test
    public void browseProjectWithoutPermission() {
        if (dashboard.isPageLoaded()) {
            driver.get(NOT_EXISTING_PROJECT);
        }
        String errorMessage = project.getErrorMessage();
        Assertions.assertEquals("You can't view this project",errorMessage);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}