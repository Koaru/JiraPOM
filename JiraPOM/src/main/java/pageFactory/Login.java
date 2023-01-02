package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {
    WebDriver driver;
    WebDriverWait wait;

    public Login(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "login-form-username")
    WebElement username;
    @FindBy (id = "login-form-password")
    WebElement password;
    @FindBy (id = "login")
    WebElement loginBtn;


    public void setUsername(String name){
        username.sendKeys(name);
    }

    public void setPassword(String pwd){
        password.sendKeys(pwd);
    }

    public void clickOnLoginBtn(){
        loginBtn.click();
    }
}