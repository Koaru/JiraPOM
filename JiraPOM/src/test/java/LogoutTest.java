import org.junit.jupiter.api.*;
import pageFactory.*;

import java.net.MalformedURLException;

public class LogoutTest {

    static LoginPage loginPage;
    static final String VALID_USERNAME = System.getProperty("username");
    static final String VALID_PASSWORD = System.getProperty("password");
    final String EXPECTED_MESSAGE = "You are now logged out. Any automatic login has also been stopped.\n" +
            "Didn't mean to log out? Log in again.";
    @BeforeEach
    public void init() throws MalformedURLException {
        loginPage = new LoginPage();
        loginPage.navigateToLoginPage();
        loginPage.loggingIn(VALID_USERNAME,VALID_PASSWORD);
    }

    @Test
    public void logout() throws MalformedURLException {
        DashboardPage dashboardPage = new DashboardPage();
        dashboardPage.clickOnProfileBtn();
        dashboardPage.clickOnLogoutBtn();
        Assertions.assertEquals(dashboardPage.logoutMessage(),EXPECTED_MESSAGE);
    }

    @AfterEach
    public void tearDown(){
        loginPage.quit();
    }
}
