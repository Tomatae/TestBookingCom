package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarField {

    private By locator;

    private WebDriver driver;

    public CalendarField(By locator, WebDriver driver)  {
        this.locator = locator;
        this.driver = driver;
    }

    public void newDate(int date) {
        WebElement mainElement = driver.findElement(locator);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        cal.add(Calendar.DATE, date);

        WebElement day = mainElement.findElement(By.xpath("//td[@data-date='" + sdf.format(cal.getTime()) + "']"));
        day.click();
    }
}
