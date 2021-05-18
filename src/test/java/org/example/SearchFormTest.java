package org.example;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchFormTest {
    public static MainPage mainPage;
    public static ResultPage resultPage;

    @BeforeClass
    public static void start(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        resultPage = new ResultPage(driver);
        driver.manage().window().maximize();
        driver.get("https://www.booking.com/");
    }

    @Test
    public void searchFormTest() {
        mainPage.waitSomeTime(1500);//Time in ms to let the page work properly
        mainPage.inputDestiny("Москва");
        mainPage.skipPetulantCookies();
        mainPage.setDates(1, 7);//Amount of days before moving in, amount of days before moving out
        mainPage.fillAccommodation(8, 2, 2, "3,7");//Adults, Kids, Rooms, Age
        mainPage.clickCheckButton();

        //Assert.assertEquals(resultPage.getResultsAmount(), 25);
        Assert.assertEquals(resultPage.getShownResultsAmount(), 25);
    }

    @AfterClass
    public static void shutDown() {
        resultPage.closeDriver();
    }
}
