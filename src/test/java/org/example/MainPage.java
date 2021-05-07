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
    @FindBy(xpath = "//div[@data-component='search/group/group-with-modal']")
    private WebElement accommodationField;

    @FindBy(css = "div.sb-group__field-adults span.bui-stepper__display")
    private WebElement adultsAmount;
    @FindBy(xpath = "//button[@aria-label='Взрослых: увеличить количество']")
    private WebElement increaseAdultsAmount;
    @FindBy(xpath = "//button[@aria-label='Взрослых: уменьшить количество']")
    private WebElement decreaseAdultsAmount;

    @FindBy(css = "div.sb-group-children span.bui-stepper__display")
    private WebElement kidsAmount;
    @FindBy(xpath = "//button[@aria-label='Детей: увеличить количество']")
    private WebElement increaseKidsAmount;
    @FindBy(xpath = "//button[@aria-label='Детей: уменьшить количество']")
    private WebElement decreaseKidsAmount;

    @FindBy(css = "div.sb-group__field-rooms span.bui-stepper__display")
    private WebElement roomsAmount;
    @FindBy(xpath = "//button[@aria-label='Номера: увеличить количество']")
    private WebElement increaseRoomsAmount;
    @FindBy(xpath = "//button[@aria-label='Номера: уменьшить количество']")
    private WebElement decreaseRoomsAmount;
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

    public void setAccommodation(int adults, int kids, int rooms, int age) {
        accommodationField.click();
        adults = adults - Integer.parseInt(adultsAmount.getText());
        kids = kids - Integer.parseInt(kidsAmount.getText());
        rooms = rooms - Integer.parseInt(roomsAmount.getText());
        if (adults != 0){
            if (adults > 0){
                for (int i = 0; i < adults; i++) {
                    increaseAdultsAmount.click();
                }
            } else {
                for (int i = adults; i < 0; i++) {
                    decreaseAdultsAmount.click();
                }
            }
        }
        if (kids != 0){
            if (kids > 0){
                for (int i = 0; i < kids; i++) {
                    increaseKidsAmount.click();
                }
            } else {
                for (int i = kids; i < 0; i++) {
                    decreaseKidsAmount.click();
                }
            }
        }
        if (rooms != 0){
            if (rooms > 0){
                for (int i = 0; i < rooms; i++) {
                    increaseRoomsAmount.click();
                }
            } else {
                for (int i = rooms; i < 0; i++) {
                    decreaseRoomsAmount.click();
                }
            }
        }
        WebElement kid0 = driver.findElement(By.xpath("//select[@data-group-child-age='0']"));
        Select kid0Select = new Select(kid0);

        if (age != Integer.parseInt(driver.findElement(By.cssSelector("[data-group-child-age='0'] option[selected]")).getText().replaceAll("[^0-9]", ""))){
            kid0Select.selectByValue(String.valueOf(age));
        }
    }

    public void clickCheckButton() {
        checkButton.click();
    }
}
