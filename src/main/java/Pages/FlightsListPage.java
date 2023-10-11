package Pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;

public class FlightsListPage extends Base {

    @FindBy(xpath="//*[contains(text(),\"Sort & Filter\")]")
    protected WebElement sortAndFilterButton;

    @FindBy(xpath="//*[@data-test-id=\"select-link\"]")
    protected WebElement firstAvailableFlight;

    @FindBy(xpath="//*[@stid=\"select-button\"]")
    protected WebElement confirmFlight;

    @FindBy(xpath="(//*[@class=\"uitk-spacing uitk-spacing-padding-blockstart-three\"]//*[@class=\"uitk-heading uitk-heading-5\"])[3]")
    protected WebElement flightAmount;

    int totalAmount = 0;

    public FlightsListPage(){
        PageFactory.initElements(driver,this);
    }

    public void confirmPageLoad(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),\"Filter by\")]")));
    }

    public int flightConfirm() throws InterruptedException {
        String flightText = firstAvailableFlight.getText();
        Pattern pat = Pattern.compile("\\$[0-9]+");
        Matcher m = pat.matcher(flightText);

        String amount = null;
        if (m.find()) {
            amount= m.group(0);
        }
        click(firstAvailableFlight);
        click(confirmFlight);
        assert amount != null;
        return Integer.parseInt(amount.substring(1));
    }

    public int selectFlights() throws InterruptedException {
        for(int i=0;i<3; i++){
            totalAmount += flightConfirm();
        }
        return totalAmount;
    }

    public void checkAmountsMatching(){
        int intFlightAmount = Integer.parseInt(flightAmount.getText().substring(1));
        Assert.assertEquals(totalAmount, intFlightAmount);
    }

    public void switchToNewlyOpenedTab(){
        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();
        assert driver.getWindowHandles().size() == 1;
        driver.findElement(By.linkText("new window")).click();
        wait.until(numberOfWindowsToBe(2));

        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }
}
