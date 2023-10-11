package StepDefinitions;

import Pages.ExpediaPage;
import Pages.FlightsListPage;
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
    FlightsListPage flightsListPage = new FlightsListPage();

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

    @And("clicks search button")
    public void clicksSearchButton() throws InterruptedException {
        expediaPage.searchForFlights();
        expediaPage.flightsLoaded();
    }

    @When("Adds a first flight from {string} to {string} of date {string}")
    public void addsAFirstFlightFromToOfDate(String destA, String destB, String date) throws IOException, InterruptedException {
        expediaPage.firstFlight(destA, destB, date);
    }

    @And("Adds a second flight from {string} to {string} of date {string}")
    public void addsASecondFlightFromToOfDate(String destB, String destC, String date) throws IOException, InterruptedException {
        expediaPage.secondFlight(destB, destC, date);
    }

    @And("Adds a third flight from {string} to {string} of date {string}")
    public void addsAThirdFlightFromToOfDate(String destC, String destA, String date) throws IOException, InterruptedException {
        expediaPage.thirdFlight(destC, destA, date);
    }

    @And("clicks to {string} adults")
    public void clicksToAdults(String count) throws InterruptedException {
        expediaPage.selectAdultCount(Integer.parseInt(count));
    }

    @Then("flights loaded")
    public void flightsLoaded() throws InterruptedException {
        expediaPage.flightsLoaded();
    }

}
