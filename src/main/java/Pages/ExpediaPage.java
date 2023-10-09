package Pages;

import base.Base;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.ArrayList;
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
    protected WebElement citySearchFieldOrg;

    @FindBy(xpath="//input[@data-stid=\"destination_select-menu-input\"]\n")
    protected WebElement citySearchFieldDest;

    @FindBy(xpath="//*[@id=\"onetrust-reject-all-handler\"]")
    protected WebElement rejectCookies;

    @FindBy(xpath="//button[@class=\"uitk-link uitk-link-align-left uitk-link-layout-default uitk-link-medium\"]")
    protected WebElement addFlightButton;

    @FindBy(xpath="//*[@class=\"uitk-fake-input uitk-form-field-trigger\"]")
    protected WebElement fromCityGeneric;

    @FindBy(xpath="(//*[@class=\"uitk-fake-input uitk-form-field-trigger\"])[2]")
    protected WebElement fromCityGeneric2;

    @FindBy(xpath="(//*[@class=\"uitk-fake-input uitk-form-field-trigger\"])[4]")
    protected WebElement fromCityGeneric3;

    @FindBy(xpath="(//*[@class=\"uitk-fake-input uitk-form-field-trigger\"])[5]")
    protected WebElement fromCityGeneric4;

    @FindBy(xpath="(//*[@class=\"uitk-field has-floatedLabel-label has-icon\"])[5]")
    protected WebElement fromCityGeneric5;

    @FindBy(xpath="(//*[@class=\"uitk-field has-floatedLabel-label has-icon\"])[6]")
    protected WebElement fromCityGeneric6;

    @FindBy(xpath="//button[@id=\"search_button\"]")
    protected WebElement searchButton;

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

    public void addFlight() throws InterruptedException, IOException {
        click(addFlightButton);
    }

    public void firstFlight(String destA, String destB) throws InterruptedException, IOException {
        click(fromCityGeneric);
        write(citySearchFieldOrg,destA);
        waitAndSelectCityOrg();
        click(fromCityGeneric2);
        citySearchFieldDest.sendKeys(destB);
        waitAndSelectCityDest();
    }

    public void secondFlight(String destB, String destC) throws InterruptedException, IOException {
        click(fromCityGeneric3);
        write(citySearchFieldOrg,destB);
        waitAndSelectCityOrg();
        click(fromCityGeneric4);
        write(citySearchFieldDest,destC);
        waitAndSelectCityDest();
    }

    public void thirdFlight(String destC, String destA) throws InterruptedException, IOException {
        click(fromCityGeneric5);
        write(citySearchFieldOrg,destC);
        waitAndSelectCityOrg();
        click(fromCityGeneric6);
        write(citySearchFieldDest,destA);
        waitAndSelectCityDest();
    }

    public void searchForFlights() throws InterruptedException {
        click(searchButton);
    }

    public void waitSearchFieldFindsCity() throws InterruptedException {
        waiting(3000);
    }

    public void selectCityOrgAfterSearch(){
        citySearchFieldOrg.sendKeys(Keys.ARROW_DOWN);
        citySearchFieldOrg.sendKeys(Keys.ENTER);
    }

    public void selectCityDestAfterSearch(){
        citySearchFieldDest.sendKeys(Keys.ARROW_DOWN);
        citySearchFieldDest.sendKeys(Keys.ENTER);
    }

    public void waitAndSelectCityOrg() throws InterruptedException {
        waitSearchFieldFindsCity();
        selectCityOrgAfterSearch();
    }

    public void waitAndSelectCityDest() throws InterruptedException {
        waitSearchFieldFindsCity();
        selectCityDestAfterSearch();
    }
}
