import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageFactory.*;

public class LoginTest {
    WebDriver driver;
    Login login;
    final String URL = "https://jira-auto.codecool.metastage.net/secure/Dashboard.jspa";

    @BeforeAll
    public void setup(){
        driver = new ChromeDriver();
        login = new Login(driver);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test
    public void validLogin(){

    }
}
