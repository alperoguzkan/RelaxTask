package StepDefinitions;

import Pages.MainPage;
import base.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static io.restassured.RestAssured.*;
import static org.hamcrest.core.IsEqual.equalTo;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import org.hamcrest.Matchers.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import utils.RequestHelper;
import utils.ResourceHelper;

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

    @Given("user writes his mail {string} and password {string}")
    public void userWritesHisMailAndPassword(String mail, String password) throws InterruptedException {
        mainPage.enterCredentials(mail, password);
    }

    @Given("user sends a post request with payload")
    public void userSendsAPostRequestWithPayload() throws IOException {

        String url = "https://reqres.in/api/register"; // Fetching url from Properties file

        JSONObject json = new JSONObject();

        json.put("email", "eve.holt@reqres.in");
        json.put("password","pistol");
        Response response = ResourceHelper.create(url, String.valueOf(json));
        logSystemOut("dsadas");
        logSystemOut(String.valueOf(response));
        // Validating the Response Code
        Assert.assertEquals(response.getStatusCode(), 200);
        logSystemOut(response.getBody().print());
        JsonPathConfig jsonPathConfig = new JsonPathConfig();

    }

    @And("closes browser")
    public void closesBrowser() {
        driver.quit();
    }


    @Given("user sends a post request with payload with email as {string} and password as {string}")
    public void userSendsAPostRequestWithPayloadWithEmailAsAndPasswordAs(String mail, String password) {
        String url = "https://reqres.in/api/register";

        JSONObject json = new JSONObject();

        json.put("email", mail);
        json.put("password",password);
        Response response = ResourceHelper.create(url, String.valueOf(json));
        logSystemOut(String.valueOf(response));
        // Validating the Response Code
        Assert.assertEquals(response.getStatusCode(), 200);
        logSystemOut(response.getBody().print());
        JsonPathConfig jsonPathConfig = new JsonPathConfig();
    }

    @Given("user sends a post request with payload with email as {string} and password as {string} and gets {string}")
    public void userSendsAPostRequestWithPayloadWithEmailAsAndPasswordAsAndGets(String mail, String password, String responseCode) {
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
        logSystemOut(response.getBody().print());

    }


    @Given("user gets a request get the user list and gets {string}")
    public void userGetsARequestGetTheUserListAndGets(String responseCode) {
        String url = "https://reqres.in/api/register";
        Response response = ResourceHelper.get(url);
        Assert.assertEquals(response.getStatusCode(), Integer.parseInt(responseCode));
        logSystemOut(response.getBody().print());
    }
}
