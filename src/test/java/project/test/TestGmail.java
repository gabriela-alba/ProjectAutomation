package project.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import project.classes.DRIVERS;
import project.classes.LoginGmail;
import project.classes.Utils;

public class TestGmail {
    private WebDriver driver;
    private LoginGmail browser;

    private final By profile_icon = By.xpath("//*[@id=\"gb\"]/div[2]/div[3]/div[1]/div[2]/div/a");

    private static final Logger logger = LogManager.getLogger(TestGmail.class);

    @BeforeTest
    public void navigateToPage(){
        browser = new LoginGmail(driver);
        driver = browser.CreateMyDriver(DRIVERS.CHROME);

        logger.info("Test is starting...\n");
        logger.info("Navigate to {} - Driver version: {} \n",
                Utils.URL_GMAIL, ((RemoteWebDriver)driver).getCapabilities().getVersion());

        browser.visitTo(Utils.URL_GMAIL);
    }

    @Test(testName = "Login Gmail")
    public void loginTest(){
        browser.login("test.emailok01@gmail.com", "testEmail98765"); //For negative tests modify the email or password

        try{
            WebDriverWait wait = new WebDriverWait(browser.getDriver(), 5);
            wait.until(ExpectedConditions.visibilityOfElementLocated(profile_icon));
        } catch (TimeoutException t){
            logger.error("Failed to logging");
        }finally {
            boolean isProfileIcon = driver.findElement(profile_icon).isDisplayed();
            Assert.assertTrue(isProfileIcon);
            logger.info("PASSED: Login to gmail \n");
        }

    }

    @AfterTest
    public void tearDown(){
        logger.info("Closing the driver.");

        driver.quit();
        logger.info("Test is ending.");
    }
}
