package StepDefinitions;

import Pages.MainPage;
import base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class MainPageSteps extends Base {

    MainPage mainPage = new MainPage();

    @Given("user clicks login button")
    public void userClicksLoginButton() throws InterruptedException {
        mainPage.clickLogin();
    }


    @Then("error {string} is displayed")
    public void errorIsDisplayed(String text) throws InterruptedException {
        mainPage.errorNoCredentials(text);
    }

    @And("user writes his mail {string} and password {string}")
    public void userWritesHisMailAndPassword(String mail, String password) throws InterruptedException {
        mainPage.enterCredentials(mail, password);
    }

    @Given("user goes to url {string}")
    public void userGoesToUrl(String arg0) {
    }

}
