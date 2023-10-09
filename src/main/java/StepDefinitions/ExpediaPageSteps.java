package StepDefinitions;

import Pages.ExpediaPage;
import Pages.MainPage;
import base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.ResourceHelper;

import java.io.IOException;

public class ExpediaPageSteps extends Base {

    ExpediaPage expediaPage = new ExpediaPage();


    @Given("user goes to flights tab")
    public void userGoesToFlightsTab() throws IOException, InterruptedException {
        expediaPage.goToFlights();
    }

    @And("clicks to multi-city option")
    public void clicksToMultiCityOption() throws IOException, InterruptedException {
        expediaPage.goToMultiCity();
    }

    @And("Clicks to Add another flight")
    public void clicksToAddAnotherFlight() throws IOException, InterruptedException {
        expediaPage.addFlight();
    }

    @When("Adds a first flight from {string} to {string}")
    public void addsAFirstFlightFromTo(String destA, String destB) throws IOException, InterruptedException {
        expediaPage.firstFlight(destA, destB);
    }


    @And("Adds a second flight from {string} to {string}")
    public void addsASecondFlightFromTo(String destB, String destC) throws IOException, InterruptedException {
        expediaPage.secondFlight(destB, destC);
    }

    @And("Adds a third flight from {string} to {string}")
    public void addsAThirdFlightFromTo(String destC, String destA) throws IOException, InterruptedException {
        expediaPage.thirdFlight(destC, destA);
    }

    @And("clicks search button")
    public void clicksSearchButton() throws InterruptedException {
        expediaPage.searchForFlights();
    }
}
