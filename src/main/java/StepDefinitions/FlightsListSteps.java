package StepDefinitions;

import Pages.ExpediaPage;
import base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class FlightsListSteps extends Base {
    Pages.FlightsListPage flightsListPage = new Pages.FlightsListPage();


    @And("check is page is redirected")
    public void checkIsPageIsRedirected() {
        flightsListPage.confirmPageLoad();
    }

    @And("select first flight for each flight")
    public void selectFirstFlightForEachFlight() throws InterruptedException {
        flightsListPage.selectFlights();
    }

    @And("check if sum of individual flights are matching with final amount")
    public void checkIfSumOfIndividualFlightsAreMatchingWithFinalAmount() {
        flightsListPage.switchToNewlyOpenedTab();
        flightsListPage.checkAmountsMatching();
    }
}
