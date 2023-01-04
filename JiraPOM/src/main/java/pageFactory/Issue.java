package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Issue {
    WebDriver driver;
    WebDriverWait wait;

    public Issue(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        PageFactory.initElements(driver, this);
    }

    @FindBy (id = "key-val")
    WebElement issueKey;
    @FindBy (id = "opsbar-operations_more")
    WebElement moreBtn;
    @FindBy (xpath = "//aui-item-link[@id='delete-issue']//a[@role='menuitem']")
    WebElement deleteBtn;
    @FindBy (id = "delete-issue-submit")
    WebElement deleteIssueBtn;

    public String getIssueKey(){
        wait.until(ExpectedConditions.elementToBeClickable(issueKey));
        return issueKey.getText();
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public void clickOnMoreBtn(){
        moreBtn.click();
    }

    public void clickOnDeleteBtn(){
        moreBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        deleteBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteIssueBtn));
        deleteIssueBtn.click();
    }

}
