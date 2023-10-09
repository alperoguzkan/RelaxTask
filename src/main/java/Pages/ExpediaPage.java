package Pages;

import base.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

import static base.Base.driver;
import static base.Base.getConf;

public class ExpediaPage extends Base {
    @FindBy(xpath="//a[@href=\"/Flights\" and @aria-controls]")
    protected WebElement flightsButton;

    @FindBy(xpath="//a[@href=\"#FlightSearchForm_MULTI_CITY\"]")
    protected WebElement multiCityButton;

    @FindBy(xpath="(//div[@class=\"uitk-typeahead\"])//*[@class=\"uitk-field has-floatedLabel-label has-icon\"]")
    protected WebElement cityGeneric; //odd values

    @FindBy(xpath="//form//button[@data-stid=\"destination_select-dialog-trigger\"]")
    protected List<WebElement> goingToCityGeneric;

    @FindBy(xpath="//input[@data-stid=\"origin_select-menu-input\"]\n")
    protected WebElement citySearchField;

    @FindBy(xpath="//*[@id=\"onetrust-reject-all-handler\"]")
    protected WebElement rejectCookies;

    @FindBy(xpath="(//*[@class=\"uitk-field has-floatedLabel-label has-icon\"])[1]")
    protected WebElement fromCityGeneric;

    @FindBy(xpath="(//*[@class=\"uitk-field has-floatedLabel-label has-icon\"])[3]")
    protected WebElement fromCityGeneric2;

    public ExpediaPage(){
        PageFactory.initElements(driver,this);
    }

    public void goToFlights() throws InterruptedException, IOException {
        driver.get(getConf("urlExpedia"));
        click(rejectCookies);
        click(flightsButton);
    }

    public void goToMultiCity() throws InterruptedException, IOException {
        click(multiCityButton);
    }

    public void firstFlight(String destA, String destB) throws InterruptedException, IOException {
        click(fromCityGeneric);
        write(citySearchField,destA);
        waiting(5000);
        citySearchField.sendKeys(Keys.ARROW_DOWN);
        citySearchField.sendKeys(Keys.ENTER);
        click(fromCityGeneric2);
    }
}
