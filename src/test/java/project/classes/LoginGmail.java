package project.classes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginGmail extends PageObject {
    WebDriverWait wait;

    private final By email_box = By.id("identifierId");
    private final By next_button = By.xpath("//*[@id=\"identifierNext\"]");
    private final By pass_box = By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input");
    final By submit_button = By.xpath("//*[@id=\"passwordNext\"]");

    private static final Logger logger = LogManager.getLogger(LoginGmail.class);

    public LoginGmail(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password){

        if (!email.equals("") && !password.equals("")){
            type(email, email_box);
            click(next_button);

            wait = new WebDriverWait(getDriver(), 5);
            WebElement passbox = wait.until(ExpectedConditions.visibilityOfElementLocated(pass_box));

            passbox.click();
            type(password, pass_box);
            click(submit_button);

            logger.info("Logged with this credentials: ");
            logger.info("Email: {}", email);
            logger.info("Password: {}\n", password);

        } else {
            logger.info("Logged with invalid credentials: ");
            logger.error("Enter an email or password");
        }
    }
}
