package StepDefinitions;

import base.Base;
import io.cucumber.java.en.And;

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
