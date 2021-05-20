package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextField {

    private By locator;

    private WebDriver driver;

    public TextField(By locator, WebDriver driver)  {
        this.locator = locator;
        this.driver = driver;
    }

    public void fill(String key) {
        WebElement mainElement = driver.findElement(locator);

        mainElement.sendKeys(key);
    }

}
