package org.example;

import org.example.elements.CalendarField;
import org.example.elements.NumberField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class MainPage {
    public WebDriver driver;

    private NumberField adults;
    private NumberField kids;
    private NumberField rooms;
    private CalendarField dateField;

    public MainPage(WebDriver driver)  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        adults = new NumberField(By.xpath("//div[./input[@id='group_adults']]"), driver);
        kids = new NumberField(By.xpath("//div[./input[@id='group_children']]"), driver);
        rooms = new NumberField(By.xpath("//div[./input[@id='no_rooms']]"), driver);

        dateField = new CalendarField(By.xpath("//div[@class='xp__dates xp__group']"), driver);
    }

    //####################
    @FindBy(xpath = "//button[@id='onetrust-accept-btn-handler']")
    private WebElement petulantCookies;
    //####################
    @FindBy(xpath = "//div[@class='xp__dates xp__group']")
    private WebElement calendarField;
    //####################
    @FindBy(xpath= "//input[@id='ss']")
    private WebElement destinyField;
    //####################
    @FindBy(xpath = "//div[@data-component='search/group/group-with-modal']")
    private WebElement accommodationField;
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

    public void setDates(int in, int out) {
        calendarField.click();

        this.dateField.newDate(in);
        this.dateField.newDate(out);
    }

    public void fillAccommodation(int adults, int kids, int rooms, String kidsAge) {
        accommodationField.click();

        this.adults.newValue(adults);
        this.kids.newValue(kids);
        this.rooms.newValue(rooms);

        String[] age = kidsAge.split(",");

        for (int i = 0; i < kids; i++) {
            Select kidSelect = new Select(driver.findElement(By.xpath("//select[@data-group-child-age='" + i + "']")));
            if (Integer.parseInt(age[i]) != Integer.parseInt(driver.findElement(By.cssSelector("[data-group-child-age='" + i + "'] option[selected]")).getText().replaceAll("[^0-9]", ""))){
                kidSelect.selectByValue(String.valueOf(age[i]));
            }
        }
    }

    public void clickCheckButton() {
        checkButton.click();
    }
}
