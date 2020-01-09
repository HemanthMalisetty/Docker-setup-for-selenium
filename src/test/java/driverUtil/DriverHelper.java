package driverUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverHelper extends DriverFactory {

    WebDriver driver;

    public DriverHelper(WebDriver driver){
        this.driver = driver;
    }

    public static void click(WebDriver driver, By by) {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }
    public static void explicitWaitForTheElementToBeClickable(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void explicitWaitForTheElementToBeVisible(WebDriver driver, By locator){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public boolean isDisplayed(WebDriver driver, By locator){
        try{
            return driver.findElement(locator).isDisplayed();
        }catch (Exception e){
            return false;
        }
    }
}
