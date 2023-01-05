package pageFactory;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Project {

    WebDriver driver;
    WebDriverWait wait;


    public Project(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "body > div:nth-child(1) > div:nth-child(2) > div:nth-child(2) > div:nth-child(1) > main:nth-child(1) > section:nth-child(2) > div:nth-child(1) > div:nth-child(2) > dl:nth-child(1) > dd:nth-child(4)")
    WebElement key;

    @FindBy(css = ".projects-error-page-heading")
    WebElement errorMessage;

    public String getKey() {
        wait.until(ExpectedConditions.visibilityOf(key));
        return key.getText();
    }

    public String getErrorMessage() {
        wait.until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }

}

