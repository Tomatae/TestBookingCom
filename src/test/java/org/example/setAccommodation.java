package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class setAccommodation {
    public WebDriver driver;
    public setAccommodation(WebDriver driver)  {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

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

    public void fillAccommodation(int adults, int kids, int rooms, String kidsAge) {

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

        String[] age = kidsAge.split(",");

        for (int i = 0; i < kids; i++) {
            Select kidSelect = new Select(driver.findElement(By.xpath("//select[@data-group-child-age='" + i + "']")));
            if (Integer.parseInt(age[i]) != Integer.parseInt(driver.findElement(By.cssSelector("[data-group-child-age='" + i + "'] option[selected]")).getText().replaceAll("[^0-9]", ""))){
                kidSelect.selectByValue(String.valueOf(age[i]));
            }
        }

    }
}

