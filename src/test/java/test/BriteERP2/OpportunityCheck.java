package test.BriteERP2;

import utilities.BriteERPUtil;
import utilities.Sleep;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpportunityCheck {

    //User story: The system should display the correct information for each opportunity on the view list page and the pivot table.

    //1.Verify that second opportunity’ Expected Revenue value on the Pivot board should be the same as the Expected
    // revenue column value on the list board.


    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    @AfterMethod
    public void tearDown() {

        //      driver.quit();

    }

    //1.Verify that second opportunity’ Expected Revenue value on the Pivot board should be the same as the Expected
    // revenue column value on the list board.
    @Test

    public void secondOpportunity() {
        BriteERPUtil.login(driver, "eventscrmmanager38@info.com", "eventscrmmanager");
        //click CRM
        driver.findElement(By.xpath("//span[@class='oe_menu_text'][contains(text(), 'CRM')]")).click();

        Sleep.sleep(4);
        driver.findElement(By.xpath("//button[@type='button' and @aria-label='pivot']")).click();

        driver.findElement(By.xpath("//table/tbody/tr[2]/td")).click();
        //click oportunity to see the all price
        driver.findElement(By.linkText("Opportunity")).click();
        //  get the first  price
        String thirdcell = driver.findElement(By.xpath("//table/tbody/tr[3]/td[2]")).getText();
        // System.out.println(thirdcell);
        //500
        driver.findElement(By.xpath("//button[@type='button' and @aria-label='list']")).click();

        Sleep.sleep(5);
        int index = 0;
        List<WebElement> opportunity = driver.findElements(By.xpath("//table/tbody/tr/td[3]"));

        opportunity.forEach((column -> System.out.println(column.getText())));

        List<String> oppStr = new ArrayList<>();

        for (int i = 0; i < opportunity.size(); i++) {

            if (opportunity.get(i).getText().equals("first deal")) {
                index = i + 1;


            }

        }
        //click list view


        Sleep.sleep(5);

        String revenue = driver.findElement(By.xpath("//table/tbody/tr[" + index + "]/td[9]")).getText();

        Assert.assertEquals(revenue, thirdcell);

//for list
////table/tfoot/tr/td[9]

    }

    @Test

    //2. Verify that on the pivot table, the total expected revenue should be the sum of all opportunities’ expected revenue.
    public void sumofPivot() {
        BriteERPUtil.login(driver, "eventscrmmanager38@info.com", "eventscrmmanager");
        //click CRM
        driver.findElement(By.xpath("//span[@class='oe_menu_text'][contains(text(), 'CRM')]")).click();

        Sleep.sleep(4);
        driver.findElement(By.xpath("//button[@type='button' and @aria-label='pivot']")).click();
        Sleep.sleep(4);
        String totalinPivot1 = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText();

        System.out.println(totalinPivot1);
        Double totalpivotDouble1 = Double.parseDouble(totalinPivot1.substring(0, totalinPivot1.length() - 2).replace(",", ""));

        String totalinPivot = driver.findElement(By.xpath("//table/tbody/tr[2]/td[2]")).getText();
        Double totalpivotDouble = Double.parseDouble(totalinPivot.substring(0, totalinPivot.length() - 2).replace(",", ""));


        // System.out.println(totalinPivot);

        Sleep.sleep(4);
        driver.findElement(By.xpath("//table/tbody/tr[2]/td")).click();
        //click oportunity to see the all price
        driver.findElement(By.linkText("Opportunity")).click();
        Sleep.sleep(5);
        List<String> totalStr = new ArrayList<>();
        List<WebElement> total = driver.findElements(By.xpath("//table/tbody/tr/td[2]"));

        for (WebElement t : total) {
            totalStr.add(t.getText());
           // System.out.println(t.getText());
        }
        //System.out.println(totalStr);


        List<Double> totaldouble = new ArrayList<>();

        for (String s : totalStr) {
            totaldouble.add(Double.parseDouble(s.substring(0, s.length() - 2).replace(",", "")));
        }
        //  System.out.println(totaldouble);


        double sum = 0;

        for (Double t : totaldouble) {
            sum += t;

        }

        //System.out.println(sum);

        double Lastsum = sum - (totalpivotDouble + totalpivotDouble1);

        Assert.assertTrue(Lastsum == totalpivotDouble);
    }

}
