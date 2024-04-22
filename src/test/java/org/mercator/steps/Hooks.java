package org.mercator.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import org.mercator.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import java.util.concurrent.TimeUnit;

public class Hooks extends Driver {

    @Before()
    public static void setup() {
        if (driver == null) {
            CreateBrowser();
        }
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }


    @After()
    public static void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                System.out.println("Failed Scenario: " + scenario.getName());
                String url = driver.getCurrentUrl();
                scenario.log(url);
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", url);

            } catch (WebDriverException e) {
                throw new WebDriverException(e.getMessage());
            }
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }

}
