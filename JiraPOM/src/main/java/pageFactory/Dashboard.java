package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Dashboard {
    WebDriver driver;
    WebDriverWait wait;

    public Dashboard(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "header-details-user-fullname")
    WebElement profileBtn;
    @FindBy (id = "view_profile")
    WebElement profile;

    public void clickOnProfileBtn(){
        profileBtn.click();
    }

    public void clickOnProfile(){
        profile.click();
    }

}
