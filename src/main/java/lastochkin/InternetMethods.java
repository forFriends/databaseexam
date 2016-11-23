package lastochkin;

import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigProperties;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InternetMethods {

    public static WebDriver driver;

    public static WebDriver getWebDriver() {
        if(driver == null){
            System.setProperty("webdriver.gecko.driver", "/home/lastochkin/geckodriver");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        return driver;}
        else return driver;
    }

    static void waitForPageLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(pageLoadCondition);
    }

    public  void getUrl() {
        driver.get(ConfigProperties.getConfigProperty("uselessfacts.url"));
        waitForPageLoad(driver);
    }


    @FindAll(@FindBy(how = How.XPATH, using = "//a[@class=\"list-group-item\"]"))
    List<WebElement> list;



    public  ArrayList<String> getTextFromWebsiteToArrayList() {

        ArrayList<String> interestingFacts = new  ArrayList<String>();

        for (int i = 0; i < list.size(); i++){
            interestingFacts.add(list.get(i).getText());
        }

        return interestingFacts;
    }
}