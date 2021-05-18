package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class MainPage {
    public WebDriver driver;
    public static setAccommodation SetAccommodation;
    public MainPage(WebDriver driver)  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    //####################
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement petulantCookies;
    //####################
    @FindBy(xpath= "//input[@id='ss']")
    private WebElement destinyField;
    //####################
    @FindBy(xpath = "//div[@class='xp__dates xp__group']")
    private WebElement dateField;
    //####################

    @FindBy(xpath = "//div[contains(@class, 'xp__button')]")
    private WebElement checkButton;


    public void waitSomeTime(int time) {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
    }

    public void inputDestiny(String destiny) {
        destinyField.sendKeys(destiny);
    }

    public void skipPetulantCookies() {
        new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(petulantCookies));
        //driver.manage().timeouts().implicitlyWait(1500, TimeUnit.MILLISECONDS);
        petulantCookies.click();
    }

    public void setDates(int a, int b) {
        dateField.click();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        cal.add(Calendar.DATE, a);
        driver.findElement(By.xpath("//td[@data-date=\'" + sdf.format(cal.getTime()) + "\']")).click();

        cal.add(Calendar.DATE, b-a);
        driver.findElement(By.xpath("//td[@data-date=\'" + sdf.format(cal.getTime()) + "\']")).click();
    }

    public void fillAccommodation(int adults, int kids, int rooms, String kidsAge) {
        SetAccommodation.fillAccommodation(adults, kids, rooms, kidsAge);
    }

    public void clickCheckButton() {
        checkButton.click();
    }
}
