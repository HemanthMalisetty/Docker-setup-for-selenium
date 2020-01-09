package driverUtil;

import com.sun.jndi.toolkit.url.Uri;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    public RemoteWebDriver driver;
    DesiredCapabilities cap;

    @BeforeTest
    @Parameters({"browser", "domain"})
    public void browserName(String browser, String domain, ITestContext context) {

        switch (browser.toLowerCase()) {
            case "chrome":
                /**
                 * chrome mobile emulator
                 */
                /*Map<String, String> mobileEmulation = new HashMap<>();

                mobileEmulation.put("deviceName", "Nexus 5");

                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

                driver = new ChromeDriver(chromeOptions);*/

                System.out.println("Browser name : " + browser);
                /*WebDriverManager.chromedriver().setup();
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--disable-notifications");
                ops.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");
                driver = new ChromeDriver(ops);*/

                cap = DesiredCapabilities.chrome();
                cap.setPlatform(Platform.WINDOWS);
                cap.setVersion("");
                try {
                    driver = new RemoteWebDriver(new URL("http://192.168.1.3:4444/wd/hub"), cap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                context.setAttribute("webDriver", driver);
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.get(domain);
                break;
            case "ff":
                System.out.println("Browser name : " + browser);
                /*WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();*/

                cap = DesiredCapabilities.firefox();
                /*cap.setCapability("version", "");*/
                /*cap.setCapability("platform", "WINDOWS");*/
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4446/wd/hub"), cap);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }

                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.get(domain);
                break;
            case "ie":
                System.out.println("Browser name : " + browser);
                WebDriverManager.iedriver().arch32().setup();
                driver = new InternetExplorerDriver();
                driver.manage().window().maximize();
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.get(domain);
                break;
        }
        LocalDriverManager.setWebDriver(driver);
    }

    @AfterTest
    public void closeApplication() {
        driver.quit();
    }
}
