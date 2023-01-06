import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;

public class BrowseProjectTestPage {
    static WebDriver driver;
    static LoginPage loginPage;
    static ProjectPage projectPage;
    static DashboardPage dashboardPage;
    static final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";
    static final String VALID_USERNAME = util.ReadFromConfig.readFromFile("VALID_USERNAME");
    static final String VALID_PASSWORD = util.ReadFromConfig.readFromFile("VALID_PASSWORD");
    static final String NOT_EXISTING_PROJECT = util.ReadFromConfig.readFromFile("NOT_EXISTING_PROJECT");

    @BeforeEach
    public void init() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        projectPage = new ProjectPage(driver);
        dashboardPage = new DashboardPage(driver);
        driver.get(URL);
        driver.manage().window().maximize();
        loginPage.loggingInInDashboard(VALID_USERNAME, VALID_PASSWORD);
    }

    public void browseProject(String expected, String URL) {
        //System.out.println(dashboard.isPageLoaded());
        if (dashboardPage.isPageLoaded()) {
            driver.get(URL);
        }
        Assertions.assertEquals(expected, projectPage.getKey());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseproject.csv")
    public void browseProjectParameterized(String url,String key){
        browseProject(key,url);
    }

    @Test
    public void browseNonExistingProject() {
        if (dashboardPage.isPageLoaded()) {
            driver.get(NOT_EXISTING_PROJECT);
        }
        String errorMessage = projectPage.getErrorMessage();
        Assertions.assertEquals("You can't view this project", errorMessage);

    }

    @Test
    public void browseProjectWithoutPermission() {
        if (dashboardPage.isPageLoaded()) {
            driver.get(NOT_EXISTING_PROJECT);
        }
        String errorMessage = projectPage.getErrorMessage();
        Assertions.assertEquals("You can't view this project", errorMessage);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}


