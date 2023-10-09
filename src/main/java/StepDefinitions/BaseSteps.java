package StepDefinitions;

import base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.testng.annotations.BeforeTest;

public class BaseSteps extends Base {

    @Before
    public void init() throws Exception {
        setup();
    }

    @After
    public void tearDown(){
        driver.quit();
    }

}
