package org.example;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Map;

public class SearchSteps {
    public static MainPage mainPage;
    public static ResultPage resultPage;

    @Before
    public void startDriver(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        resultPage = new ResultPage(driver);
        driver.manage().window().maximize();
    }

    @Given("user is on main page")
    public void openMainPage() {
        resultPage.driver.get("https://www.booking.com/");
    }

    @When("user fills the search form:")
    public void enterData(Map<String, String> data) {
        mainPage.waitSomeTime(1500);//Time in ms to let the page work properly
        mainPage.inputDestiny(data.get("destination"));
        mainPage.skipPetulantCookies();
        mainPage.setDates(Integer.parseInt(data.get("moveIn")), Integer.parseInt(data.get("moveOut")));
        mainPage.fillAccommodation(Integer.parseInt(data.get("Adults")), Integer.parseInt(data.get("Kids")), Integer.parseInt(data.get("Rooms")), data.get("KidsAge"));//Adults, Kids, Rooms, Age
    }

    @And("clicks on check button")
    public void openResultPage() {
        mainPage.clickCheckButton();
    }

    @Then("25 results shown on result page")
    public void checkTheResult() {
        Assert.assertEquals(resultPage.getShownResultsAmount(), 25);
    }

    @After
    public void close() {
        resultPage.closeDriver();
    }
}
