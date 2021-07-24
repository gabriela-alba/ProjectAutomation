package project.test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import project.classes.DRIVERS;
import project.classes.SearchEbay;
import project.classes.Utils;

public class TestEbay {
    private WebDriver driver;
    private SearchEbay page;

    private static final Logger logger = LogManager.getLogger(TestEbay.class);

    @BeforeTest
    public void navigateToPage(){
        page = new SearchEbay(driver);
        driver = page.CreateMyDriver(DRIVERS.CHROME);

        logger.info("Test is starting...\n");

        page.visitTo(Utils.URL_EBAY);
    }

    @Test(testName = "Search in Ebay")
    public void SearchItemTest(){
        page.searchItem("Guitarra Eléctrica");

        final By item_price = By.xpath("//*[@id=\"srp-river-results\"]/ul/li[1]/div/div[2]/div[2]/div[1]/span");
        Assert.assertTrue(driver.findElement(item_price).isDisplayed(), "Failed item price");
        String actualResult = driver.findElement(item_price).getText();

        logger.info("Item price: " + actualResult);
    }

    @AfterTest
    public void tearDown(){
        logger.info("Closing the driver.");

        driver.quit();
        logger.info("Test is ending.");
    }
}
