package driverUtil;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;

public class TestListener extends TestListenerAdapter {

    WebDriver driver;

    private static String fileSeperator = System.getProperty("file.seperator");

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("****** Error " + result.getName() + " test has failed ******");

        WebDriver driver = (WebDriver) result.getTestContext().getAttribute("webDriver");
        String testClassName = getClassName(result.getInstanceName()).trim();
        String testMethodName = result.getName().toString().trim();
        String screenshotName = testMethodName + ".png";
        if (driver != null) {
            String imagePath = ".." + fileSeperator + "Screenshots" + fileSeperator + "Results" + fileSeperator + testClassName + fileSeperator + takeScreenshotName(driver, screenshotName, testClassName);
            System.out.println("Screenshot can be found : " + imagePath);
        }

    }

    public static String takeScreenshotName(WebDriver driver, String screenshotName, String testName) {
        try {
            File file = new File("Screenshots" + fileSeperator + "Results");
            if (!file.exists()) {
                System.out.println("File Created " + file);
                file.mkdir();
            }
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("Screenshots " + fileSeperator + "Results" + fileSeperator + testName, screenshotName);
            FileUtils.copyFile(screenshotFile, targetFile);
            return screenshotName;
        } catch (Exception e) {
            System.out.println("An exception occord while taking screenshot " + e.getCause());
            return null;
        }
    }

    public String getClassName(String testName) {
        String[] reqTestClassName = testName.split("\\.");
        int i = reqTestClassName.length - 1;
        System.out.println("Required Test case name : " + reqTestClassName[i]);
        return reqTestClassName[i];
    }
}
