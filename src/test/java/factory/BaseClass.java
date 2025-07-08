package factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {

    private static WebDriver driver;
    private static Properties p;
    private static Logger logger;

    // Initialize browser based on config
    public static WebDriver initilizeBrowser() throws IOException {
        p = getProperties();
        String executionEnv = p.getProperty("execution_env").toLowerCase();
        String browser = p.getProperty("browser").toLowerCase();
        String os = p.getProperty("os").toLowerCase();

        if (executionEnv.equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (os) {
                case "windows":
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;
                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;
                case "linux":
                    capabilities.setPlatform(Platform.LINUX);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported OS: " + os);
            }

            switch (browser) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        } else if (executionEnv.equals("local")) {
            switch (browser) {
                case "chrome":
                	
                		ChromeOptions options = new ChromeOptions();
                		
                		options.addArguments("--headless=new");
                		options.addArguments("--no-sandbox");
                		options.addArguments("--disable-dev-shm-usage");

                		String userDataDir = System.getProperty("java.io.tmpdir") + "/profile_" + System.currentTimeMillis();
                		options.addArguments("--user-data-dir=" + userDataDir);

                    driver = new ChromeDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }

        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        return driver;
    }

    // Get the current WebDriver instance
    public static WebDriver getDriver() {
        return driver;
    }

    // Set the WebDriver instance (used in Hooks)
    public static void setDriver(WebDriver driverInstance) {
        driver = driverInstance;
    }

    // Load properties from config file
    public static Properties getProperties() throws IOException {
        if (p == null) {
            FileReader file = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
            p = new Properties();
            p.load(file);
        }
        return p;
    }

    // Get logger instance
    public static Logger getLogger() {
        if (logger == null) {
            logger = LogManager.getLogger();
        }
        return logger;
    }

    // Generate random alphabetic string
    public static String randomeString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    // Generate random numeric string
    public static String randomeNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    // Generate random number within a range
    public static int randomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Min should be less than or equal to Max");
        }
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    // Generate random alphanumeric string
    public static String randomAlphaNumeric() {
        String str = RandomStringUtils.randomAlphabetic(3);
        String num = RandomStringUtils.randomNumeric(3);
        return str + "@" + num;
    }

    // Generate random email address
    public static String randomEmail() {
        String str = RandomStringUtils.randomAlphabetic(4);
        String num = RandomStringUtils.randomNumeric(2);
        return str + num + "@" + randomeString().toLowerCase() + ".com";
    }

    // Navigate back in browser
    public static void navigateBack() {
        if (driver != null) {
            driver.navigate().back();
        }
    }

    // Simulate pressing the TAB key
    public static void pressTabKey() {
        if (driver != null) {
            Actions act = new Actions(driver);
            act.sendKeys(Keys.TAB).perform();
        }
    }
}
