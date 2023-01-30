package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends BasePage {


    public DashboardPage() {
        super();
    }

    @FindBy (id = "header-details-user-fullname")
    WebElement profileBtn;
    @FindBy (id = "view_profile")
    WebElement profile;
    @FindBy (id = "log_out")
    WebElement logoutBtn;
    @FindBy (xpath = "//div[@class='aui-message aui-message-info']")
    WebElement logoutMsg;
    @FindBy (id = "create_link")
    WebElement createBtn;
    @FindBy (id = "project-field")
    WebElement projectField;
    @FindBy (id = "issuetype-field")
    WebElement issueTypeField;
    @FindBy (id = "summary")
    WebElement summaryField;
    @FindBy (id = "create-issue-submit")
    WebElement createIssueBtn;
    @FindBy (xpath = "//button[normalize-space()='Cancel']")
    WebElement cancelIssueBtn;
    @FindBy (css = ".issue-created-key.issue-link")
    WebElement createdIssueLink;
    @FindBy (xpath = "//div[@class='aui-message closeable aui-message-success aui-will-close']")
    WebElement deleteIssuePopUp;
    @FindBy (xpath = "//div[@role='alert']")
    WebElement summaryFieldMessage;
    @FindBy (xpath = "//h2[normalize-space()='Create Issue']")
    WebElement createIssueString;
    @FindBy (id = "create-issue-dialog")
    WebElement modalWindow;

    public void clickOnProfileBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(profileBtn));
        profileBtn.click();
    }

    public void clickOnProfile(){
        profile.click();
    }

    public void clickOnLogoutBtn(){
        logoutBtn.click();
    }

    public String logoutMessage(){
        return logoutMsg.getText();
    }

    public void clickOnCreateBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(createBtn));
        createBtn.click();
    }

    public void fillSummary(String data){
        wait.until(ExpectedConditions.elementToBeClickable(summaryField));
        summaryField.sendKeys(data);
    }

    public void clickOnCreateIssueBtn(){
        wait.until(ExpectedConditions.visibilityOf(createIssueBtn));
        createIssueBtn.click();
    }

    public void clickOnCreatedIssueLink(){
        wait.until(ExpectedConditions.visibilityOf(createdIssueLink));
        createdIssueLink.click();
    }

    public String getIssueText(){
        wait.until(ExpectedConditions.elementToBeClickable(createdIssueLink));
        return createdIssueLink.getText();
    }

    public Boolean isDeleteIssueValidate(){
        wait.until(ExpectedConditions.visibilityOf(deleteIssuePopUp));
        return deleteIssuePopUp.isDisplayed();
    }

    public void clickOnCancelBtn(){
        wait.until(ExpectedConditions.visibilityOf(cancelIssueBtn));
        cancelIssueBtn.click();
    }

    public Boolean isModalVisible(){
        return createIssueBtn.isEnabled();
    }

    public Boolean isSummaryFieldEmpty(){
        wait.until(ExpectedConditions.visibilityOf(summaryFieldMessage));
        return summaryFieldMessage.isDisplayed();
    }

    public void modifyProjectField(String project){
        wait.until(ExpectedConditions.visibilityOf(projectField));
        projectField.click();
        projectField.sendKeys(project);
        createIssueString.click();
    }

    public String getProjectFieldText(){
        wait.until(ExpectedConditions.visibilityOf(projectField));
        return projectField.getText();
    }

    public Boolean isModalWindowsDisplayed(){
        wait.until(ExpectedConditions.visibilityOf(modalWindow));
        return modalWindow.isDisplayed();
    }

    public void modifyTypeField(String type){
        issueTypeField.click();
        issueTypeField.sendKeys(type);
        createIssueString.click();
    }

    public Boolean isPageLoaded(){
        wait.until(ExpectedConditions.elementToBeClickable(createBtn));
        return createBtn.isEnabled();
    }


}
