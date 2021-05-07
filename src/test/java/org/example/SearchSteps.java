package org.example;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchSteps {
    public static MainPage mainPage;
    public static ResultPage resultPage;

    @Given("user is on main page")
    public void openMainPage() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        resultPage = new ResultPage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
    }

    @When("^user goes to (.*), moves in (.*) days, moves out in (.*) days, there will be (.*) adults and (.*) kids, user needs (.*) rooms, the kids ages are (.*)$")
    public void enterData(String destination, int moveIn, int moveOut, int adults, int kids, int rooms, int kid0Age) {
        mainPage.waitSomeTime(1500);//Time in ms to let the page work properly
        mainPage.inputDestiny(destination);
        mainPage.skipPetulantCookies();
        mainPage.setDates(moveIn, moveOut);
        //Only 1 kid supported for now
        mainPage.setAccommodation(adults, kids, rooms, kid0Age);//Adults, Kids, Rooms, Age
    }

    @And("clicks on check button")
    public void openResultPage() {
        mainPage.clickCheckButton();
    }

    @Then("user is navigated to the result page")
    public void checkTheResult() {
        Assert.assertEquals(resultPage.getShownResultsAmount(), 25);
        resultPage.closeDriver();
    }
}
