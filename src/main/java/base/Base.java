package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriverWait wait;
    public static Properties prop;
    public static String url;
    public static String userDirectory = "user.dir";
    public static WebDriver driver;

    public static void setup() throws IOException, InterruptedException {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        System.setProperty("webdriver.chrome.driver", System.getProperty(userDirectory) + "\\Drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", System.getProperty(userDirectory) + "\\Drivers\\geckodriver.exe");
        System.setProperty("webdriver.edge.driver", System.getProperty(userDirectory) + "\\Drivers\\msedgedriver.exe");

        url = getConf("url");

        ChromeOptions options = new ChromeOptions();
        FirefoxOptions options2 = new FirefoxOptions();
        EdgeOptions options3 = new EdgeOptions();

        if (getConf("defaultBrowser").equals(getConf("browser_Chrome"))) {
            options.addArguments("--incognito");
            driver = new ChromeDriver(options);
        } else if (getConf("defaultBrowser").equals(getConf("browser_Firefox"))) {
            options2.addArguments("--incognito");
            driver = new FirefoxDriver(options2);
        } else if (getConf("defaultBrowser").equals(getConf("browser_Edge"))) {
            driver = new EdgeDriver(options3);
        }

        driver.manage().window().maximize();
        driver.get(url);

        int waitTime = Integer.parseInt(getConf("waitTime")) * Integer.parseInt(getConf("waitTimeMultiplier"));
        wait = new WebDriverWait(driver, waitTime);

        int implicitWait = 3;
        driver.manage().timeouts().implicitlyWait(implicitWait, TimeUnit.SECONDS);

    }

    public static String getConf(String value) throws FileNotFoundException, IOException {
        prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        String val = prop.getProperty(value);
        return val;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void click(WebElement element) throws InterruptedException {
        System.out.println("Clicking to" + element);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            waiting(300); //Making more slow to checkout the flow while cases are running
            element.click();
            if (!element.getText().isEmpty()) {
                logSystemOut("Successfully clicked on by the text: " + element.getText());
            }
        } catch (Exception e) {
            logSystemOut("Cannot clicked to: " + element);
            logSystemOut((e.getMessage()));
        }
    }

    public void jsClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            JavascriptExecutor jsDriver = (JavascriptExecutor) getDriver();
            jsDriver.executeScript("arguments[0].click();", element);
            if (!element.getText().isEmpty()) {
                logSystemOut("Successfully clicked(js) to element by the text: " + element.getText());
            } else {
                logSystemOut("Cannot clicked to the element");
            }
        }catch(Exception e){
            logSystemOut(e.getMessage());
        }
    }

    public void write(WebElement element, String text) throws InterruptedException {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(text);
            if (element.getAttribute("value").compareTo(text) != 0) {
                write(element, text);
            } else {
                if (!element.getText().isEmpty()) {
                    logSystemOut("Successfully wrote: " + text + ",to the element by the text:" + element.getText());
                } else {
                    logSystemOut("Successfully wrote: " + text + ",to the element by the xpath:" + getByFromElement(element).toString());
                }
            }
        } catch (StaleElementReferenceException e) {
            logSystemOut("Cannot written: " + text.toLowerCase());
            logSystemOut(e.getMessage());
        }
    }

    public By getByFromElement(WebElement element) {
        /**
         * This method get the By of a given WebElement
         * @param element WebElement that is to the method to be applied on
         */
        logSystemOut(element.toString());
        By by = null;
        String[] selectorWithValue = (element.toString().split("->")[1].replaceFirst("(?s)(.*)\\]", "$1" + "")).split(":(.*?)");

        String selector = selectorWithValue[0].trim();
        String value = selectorWithValue[1].trim();

        switch (selector) {
            case "id":
                by = By.id(value);
                break;
            case "className":
                by = By.className(value);
                break;
            case "tagName":
                by = By.tagName(value);
                break;
            case "xpath":
                by = By.xpath(value);
                break;
            case "cssSelector":
                by = By.cssSelector(value);
                break;
            case "linkText":
                by = By.linkText(value);
                break;
            case "name":
                by = By.name(value);
                break;
            case "partialLinkText":
                by = By.partialLinkText(value);
                break;
            default:
                throw new IllegalStateException("locator : " + selector + " not found!!!");
        }
        return by;
    }

    public void waiting(long i) throws InterruptedException {
        Thread.sleep(i);
    }

    public void logSystemOut(String logText) {
        System.out.println(logText);
    }

    public void verifyTextPresent(String text) {
        String bodyText = getDriver().findElement(By.tagName("body")).getText();
        try {
            wait.until(ExpectedConditions.textToBePresentInElement((getDriver().findElement(By.xpath("//*"))), text));
            if (bodyText.contains(text)) {
                logSystemOut("Text found: " + text);
            }
        }catch(Exception e) {
            logSystemOut("Cannot seen:  " + text);
        }

    }

}

