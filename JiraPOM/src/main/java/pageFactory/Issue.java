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
    @FindBy (id = "edit-issue")
    WebElement editIssueBtn;
    @FindBy (id = "summary")
    WebElement editIssueSummary;
    @FindBy (id = "summary-val")
    WebElement summary;
    @FindBy (id = "edit-issue-submit")
    WebElement editBtn;
    @FindBy (xpath = "//div[@role='alert']")
    WebElement editErrorMsg;
    @FindBy (xpath = "//button[normalize-space()='Cancel']")
    WebElement cancelBtn;

    public String getIssueKey(){
        wait.until(ExpectedConditions.elementToBeClickable(issueKey));
        return issueKey.getText();
    }

    public String getUrl(){
        return driver.getCurrentUrl();
    }

    public void clickOnMoreBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(moreBtn));
        moreBtn.click();
    }

    public void clickOnEditIssueBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(editIssueBtn));
        editIssueBtn.click();
    }

    public void clickOnDeleteBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(moreBtn));
        moreBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        deleteBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(deleteIssueBtn));
        deleteIssueBtn.click();
    }

    public void editSummary(String text){
        wait.until(ExpectedConditions.elementToBeClickable(editIssueSummary));
        editIssueSummary.clear();
        editIssueSummary.sendKeys(text);
    }

    public String getSummary(){
        wait.until(ExpectedConditions.elementToBeClickable(summary));
        return summary.getText();
    }

    public void clickOnUpdateBtn(){
        wait.until((ExpectedConditions.elementToBeClickable(editBtn)));
        editBtn.click();
    }

    public Boolean editIssueErrorIsPresent(){
        wait.until(ExpectedConditions.visibilityOf(editErrorMsg));
        return editErrorMsg.isDisplayed();
    }

    public void clickOnCancelBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn));
        cancelBtn.click();
    }

    public void clickOnCancelButtonAndAcceptAlert(){
        wait.until(ExpectedConditions.elementToBeClickable(cancelBtn));
        cancelBtn.click();
        driver.switchTo().alert().accept();
    }

}
