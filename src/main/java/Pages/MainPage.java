package Pages;

import base.Base;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Base {

    @FindBy(xpath="//input[@id='login-button']")
    protected WebElement loginButton;

    @FindBy(xpath="//input[@id='user-name']")
    protected WebElement userNameBox;

    @FindBy(xpath="//input[@id='password']")
    protected WebElement passwordBox;

    public MainPage(){
        PageFactory.initElements(driver,this);
    }

    public void clickLogin() throws InterruptedException {
        click(loginButton);
    }

    public void errorNoCredentials(String text) throws InterruptedException {
        verifyTextPresent(text);
    }

    public void enterCredentials(String mail, String password) throws InterruptedException {
        write(userNameBox, mail);
        write(passwordBox, password);
    }

}
