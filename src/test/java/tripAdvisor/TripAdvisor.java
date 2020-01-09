package tripAdvisor;

import driverUtil.DriverFactory;
import driverUtil.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.Set;

public class TripAdvisor extends DriverFactory {
    @Test
    public void tripAdvisor(){
        DriverHelper.explicitWaitForTheElementToBeClickable(driver,By.xpath("//*[@title = 'Search']"));
        driver.findElement(By.xpath("//*[@title = 'Search']")).click();
        driver.findElement(By.xpath("//*[@id = 'mainSearch']")).sendKeys("Club Mahindra");
        driver.findElement(By.id("SEARCH_BUTTON_CONTENT")).click();

        String currentWindow = driver.getWindowHandle();
        System.out.println("First Window - " + currentWindow);
        driver.findElement(By.xpath("//*[@class = 'result-title'][1]")).click();

        Set<String> windows = driver.getWindowHandles();
        String secondWindow = null;
        for (String window : windows) {
            if (!window.equals(currentWindow)){
                driver.switchTo().window(window);
                secondWindow = window;
                break;
            }
        }

        System.out.println("Second window - " + secondWindow);

        DriverHelper.explicitWaitForTheElementToBeClickable(driver, By.xpath("//*[text() = 'Write a review']"));

        driver.findElement(By.xpath("//*[text() = 'Write a review']")).click();

        windows = driver.getWindowHandles();

        String thirdWindow = null;
        for (String window : windows) {
            if (!window.equals(currentWindow)){
                if (!window.equals(secondWindow)){
                    driver.switchTo().window(window);
                    thirdWindow = window;
                    break;
                }
            }
        }

        System.out.println("Third Window -" + thirdWindow);


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('bubble_rating').setAttribute('class', 'ui_bubble_rating fl bubble_10')");



        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
