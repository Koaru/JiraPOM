package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;

public class ProfilePage extends BasePage{

    public ProfilePage() throws MalformedURLException {
        super();
    }

    @FindBy (id = "up-d-username")
    WebElement username;

    public String getUsername(){
        return username.getText();
    }
}
