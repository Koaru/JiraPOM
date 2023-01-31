import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.*;
import pageFactory.*;

public class BrowseProjectTestPage {
    static LoginPage loginPage;
    static ProjectPage projectPage;
    static DashboardPage dashboardPage;
    static final String VALID_USERNAME = System.getProperty("username");
    static final String VALID_PASSWORD = System.getProperty("password");


    @BeforeEach
    public void init() {
        loginPage = new LoginPage();
        projectPage = new ProjectPage();
        dashboardPage = new DashboardPage();
        loginPage.navigateToDashboardLoginPage();
        loginPage.loggingInInDashboard(VALID_USERNAME, VALID_PASSWORD);
    }

    public void browseProject(String expected, String URL) {
        if (dashboardPage.isPageLoaded()) {
            dashboardPage.navigate(URL);
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
            dashboardPage.navigateToNonExistentProject();
        }
        String errorMessage = projectPage.getErrorMessage();
        Assertions.assertEquals("You can't view this project", errorMessage);

    }

    @Test
    public void browseProjectWithoutPermission() {
        if (dashboardPage.isPageLoaded()) {
            dashboardPage.navigateToWithoutPermissionProject();
        }
        String errorMessage = projectPage.getErrorMessage();
        Assertions.assertEquals("You can't view this project", errorMessage);
    }

    @AfterEach
    public void tearDown() {
        loginPage.quit();
    }
}


