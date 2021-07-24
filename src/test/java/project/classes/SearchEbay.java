package project.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchEbay extends PageObject {
    WebDriverWait wait;

    private final By search_box = By.id("gh-ac");
    private final By search_button = By.id("gh-btn");
    private final By item_search = By.xpath("//*[@id=\"srp-river-results\"]/ul/li[1]/div/div[1]/div/a/div/img");

    private static final Logger logger = LogManager.getLogger(SearchEbay.class);

    public SearchEbay(WebDriver driver) {
        super(driver);
    }

    public void searchItem(String item){
        if (!item.equals("")) {
            click(search_box);
            type(item, search_box);
            click(search_button);
            try {
                wait = new WebDriverWait(getDriver(), 5);
                WebElement item_result = wait.until(ExpectedConditions.visibilityOfElementLocated(item_search));
                logger.info("Successful search");
            } catch (TimeoutException t){
                logger.error("Failed searching item");
            }
        } else {
            logger.error("Failed searching null item");
        }
    }
}
