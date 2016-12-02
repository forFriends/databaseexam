import lastochkin.InternetMethods;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;


import java.sql.SQLException;
import java.util.ArrayList;

import static lastochkin.InternetMethods.driver;
import static lastochkin.InternetMethods.getWebDriver;
import static utils.MySQLConnector.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SqlTest {
    @Test
    public void sqlInsertTest() throws SQLException {
        //Inserting sms in table sms

        //Getting expected result
        String sms10 = getResultSet("SELECT * from sms;").get(9).toString();

        assertThat("Error", "Jenifer", is(sms10));
    }

    @Test
    public void internetToSQL() {
        InternetMethods internetMethods = PageFactory.initElements(getWebDriver(), InternetMethods.class);

        internetMethods.getUrl();
        ArrayList<String> interestingFacts = internetMethods.getTextFromWebsiteToArrayList();
        executeQueries(interestingFacts);
        String sixthSql = returnOneRow("SELECT fact FROM interesting;");

        assertThat("Text is not matched!", sixthSql, is("Color tells us about the temperature of a candle flame. " +
                "The inner core of the candle flame is light blue, with a temperature of around 1670 K (1400 °C). That " +
                "is the hottest part of the flame. The colour inside the flame becomes yellow, orange, and finally red."));


    }

    @AfterMethod
    public static void tearDown()throws SQLException{
        getWebDriver().quit();
        clearTheTable("facts.interesting");
    }

}

