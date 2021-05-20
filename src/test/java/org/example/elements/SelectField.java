package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectField {

    private By locator;

    private WebDriver driver;

    public SelectField(By locator, WebDriver driver)  {
        this.locator = locator;
        this.driver = driver;
    }

    public void selectValue(String value) {
        WebElement mainElement = driver.findElement(locator);
        Select element = new Select(mainElement);

        element.selectByValue(value);
    }
}
