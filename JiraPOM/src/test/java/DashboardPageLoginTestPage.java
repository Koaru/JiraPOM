import org.junit.jupiter.api.*;
import pageFactory.*;

public class DashboardPageLoginTestPage {
    static LoginPage loginPage;

    static final String VALID_USERNAME = System.getenv("USERNAME");
    static final String VALID_PASSWORD = System.getenv("PASSWORD");

    final String INVALID_USERNAME = "invalid-username";
    final String INVALID_PASSWORD = "invalid-password";
    @BeforeEach
    public void init(){
        loginPage = new LoginPage();
        loginPage.navigateToDashboardLoginPage();
    }

    @Test
    public void validLogin(){
        DashboardPage dashboardPage = new DashboardPage();
        ProfilePage profilePage = new ProfilePage();
        loginPage.loggingInInDashboard(VALID_USERNAME,VALID_PASSWORD);
        dashboardPage.clickOnProfileBtn();
        dashboardPage.clickOnProfile();
        Assertions.assertEquals(VALID_USERNAME, profilePage.getUsername());
    }

    @Test
    public void invalidLoginWithInvalidUsername(){
        loginPage.loggingInInDashboard(INVALID_USERNAME,VALID_PASSWORD);
        Assertions.assertTrue(loginPage.usernameErrorIsPresent());
    }

    @Test
    public void invalidLoginWithInvalidPassword(){
        loginPage.loggingInInDashboard(VALID_USERNAME,INVALID_PASSWORD);
        Assertions.assertTrue(loginPage.usernameErrorIsPresent());
    }


    @AfterEach
    public void tearDown(){
        loginPage.quit();
    }
}
