import lastochkin.InternetMethods;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import utils.MySQLConnector;


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
        String fifthSql = returnOneRow("SELECT fact FROM interesting;");

        assertThat("Text is not matched!", fifthSql, is("Orville and Wilbur Wright designed and flew their" +
                " Flyer plane in December 1903. Five people were said to have witnessed the flight including John T. " +
                "Daniels who took photographic evidence. Claims that Whitehead beat the Wright Brothers to the record " +
                "first emerged in 1937"));


    }

    @AfterMethod
    public static void tearDown()throws SQLException{
        getWebDriver().quit();
        clearTheTable("facts.interesting");
    }

}

