package org.mercator;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.CapabilityType.ACCEPT_INSECURE_CERTS;

public class Driver {

    //    public void startBrowser() {
//        WebDriver browser;
//        //Firefox's geckodriver location.
//        System.setProperty("webdriver.gecko.driver", "org/mercator/geckodriver");
//        browser = new FirefoxDriver();
//        browser.get("https://www.saucedemo.com/");
////        WebElement header = browser.findElement(By.id("header"));
////        assertTrue((header.isDisplayed()));
////        browser.close();
//    }
    public static final String LINUX = "Linux";
    public static final String MAC = "Mac";
    public static WebDriver driver = CreateBrowser();

    protected static WebDriver CreateBrowser() {
        String browserType = System.getProperties().getProperty("browser", "chrome");
        switch (browserType) {
            case CHROME:
                if (getOs().startsWith(LINUX)) {
                    System.setProperty(
                            ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                            System.getProperty("user.dir")
                                    + "/src/test/java/org/mercator/Driver/linux/chromedriver");
                    return createChromeDriver();
                }
                else if (getOs().startsWith(MAC)) {
                    System.setProperty(
                            ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
                            System.getProperty("user.dir")
                                    + "/src/test/java/org/mercator/Driver/mac/chromedriver");
                    return createChromeDriver();
                }
            default:
                throw new RuntimeException("Browser Type not found");
        }
    }

    private static WebDriver createChromeDriver() {
        String headLess = System.getProperties().getProperty("headLess", "false");
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        options.addArguments("--disable-features=VizDisplayCompositor");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--start-maximized");
        options.addArguments("--no-sandbox");
        options.addArguments("--enable-javascript");
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(ACCEPT_INSECURE_CERTS, true);
        capabilities.setJavascriptEnabled(true);
        options.setHeadless(Boolean.parseBoolean(headLess));

        driver = new ChromeDriver(options);
        System.out.println(
                "BROWSER: "
                        + ((ChromeDriver) driver).getCapabilities().getBrowserName().toUpperCase()
                        + ", VERSION: "
                        + ((ChromeDriver) driver).getCapabilities().getVersion()
                        + ", OS: "
                        + System.getProperty("os.name"));
        return driver;
    }

    private static String getOs() {
        return System.getProperty("os.name");
    }



}



