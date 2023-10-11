package StepDefinitions;

import Pages.MainPage;
import base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import utils.ResourceHelper;

public class MainPageSteps extends Base {

    MainPage mainPage = new MainPage();
    JSONObject jsonResponse;
    JSONArray getJsonResponse;

    @Given("user clicks login button")
    public void userClicksLoginButton() throws InterruptedException {
        mainPage.clickLogin();
    }


    @Then("error {string} is displayed")
    public void errorIsDisplayed(String text) throws InterruptedException {
        mainPage.errorNoCredentials(text);
    }

    @Given("user writes his mail {string} and password {string}")
    public void userWritesHisMailAndPassword(String mail, String password) throws InterruptedException {
        mainPage.enterCredentials(mail, password);
    }

    @And("closes browser")
    public void closesBrowser() {
        driver.quit();
    }

    @Given("user sends a post request with payload with email as {string} and password as {string} and gets {string}")
    public void userSendsAPostRequestWithPayloadWithEmailAsAndPasswordAsAndGets(String mail, String password, String responseCode) throws ParseException {
        String url = "https://reqres.in/api/register";

        JSONObject json = new JSONObject();

        if(mail != null && !mail.isEmpty()){
            json.put("email", mail);
        }

        if(password != null && !password.isEmpty()){
            json.put("password", password);
        }
        Response response = ResourceHelper.create(url, String.valueOf(json));
        logSystemOut(String.valueOf(response));
        // Validating the Response Code
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(responseCode));

        JSONParser parser = new JSONParser();
        jsonResponse = (JSONObject) parser.parse(response.getBody().print());
        logSystemOut(response.getBody().print());
    }


    @Given("user gets a request get the user list and gets {string}")
    public void userGetsARequestGetTheUserListAndGets(String responseCode) throws ParseException {
        String url = "https://reqres.in/api/register";
        Response response = ResourceHelper.get(url);
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(responseCode));
        response.getBody().print();
        logSystemOut(response.getBody().print());
        JSONParser parser = new JSONParser();
        getJsonResponse = (JSONArray) parser.parse(response.getBody().print());
    }

    @And("gets {string} error")
    public void getsError(String errorName) {
        Assert.assertEquals(errorName,(String) jsonResponse.get("error"));
    }

    @And("gets a token")
    public void getsAToken() {
        Assert.assertNotNull(jsonResponse.get("token"));
    }

    @And("gets a list of users")
    public void getsAListOfUsers() {
        logSystemOut((String) getJsonResponse.get(Integer.parseInt("data")));
    }
}
