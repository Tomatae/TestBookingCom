package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NumberField {

    private By locator;
    private By increaseBtn = By.cssSelector("button[data-bui-ref='input-stepper-add-button']");
    private By decreaseBtn = By.cssSelector("button[data-bui-ref='input-stepper-subtract-button']");
    private By valueField = By.cssSelector("span[data-bui-ref='input-stepper-value']");

    private WebDriver driver;

    public NumberField(By locator, WebDriver driver)  {
        this.locator = locator;
        this.driver = driver;
    }

    public void newValue(int value) {
        WebElement mainElement = driver.findElement(locator);
        WebElement increase = mainElement.findElement(increaseBtn);
        WebElement decrease = mainElement.findElement(decreaseBtn);

        int currentValue = Integer.parseInt(mainElement.findElement(valueField).getText());

        if (value == currentValue) {
            return;
        }

        if (currentValue > value) {
            for (int i = 0; i < currentValue - value; i++) {
                decrease.click();
            }
        } else {
            for (int i = 0; i < value - currentValue; i++) {
                increase.click();
            }
        }

    }


}

