package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    public WebDriver driver;
    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(css = "[data-block-id=\"heading\"] h1")
    private WebElement resultsAmount;

    public int getResultsAmount() {
        String result = resultsAmount.getText();
        return Integer.parseInt(result.replaceAll("[^0-9]", ""));
    }

    public int getShownResultsAmount() {
        return driver.findElements(By.className("sr_item_default")).size();
    }

    public void closeDriver() {
        driver.quit();
    }
}
