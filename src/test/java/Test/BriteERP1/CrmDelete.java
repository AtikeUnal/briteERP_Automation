package Test.BriteERP1;

import Utilities.BriteERPUtil;
import Utilities.Sleep;
import Utilities.VerificationUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/*
 User Story : As a user, when I already created an opportunity in CRM module of Bribe ERP application,
   I want to be able to delete it.Acceptance Criteria :
   1.Verify that user should be able to see the list view.
   2.Verify that user should be able to delete the opportunity from action drop down list .
Feature: Delete the opportunity in CRM Module
Scenario: Delete the opportunity from CRM Module, when you already created it.
Given the opportunity already created in CRM Module
When the user clicks the list view
Then the user should be able to see the opportunities listed view
When the user clicks the checkbox already created opportunity
Then the user should be able to delete the opportunity from the Action dropdown
 */
public class CrmDelete {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    @AfterMethod
    public void tearDown() {

        //      driver.quit();

    }

    @Test

    //add an opportunity
    public void CreatePreConditions() throws InterruptedException {

        BriteERPUtil.login(driver, "eventscrmmanager38@info.com", "eventscrmmanager");
        //click CRM
        driver.findElement(By.xpath("//span[@class='oe_menu_text'][contains(text(), 'CRM')]")).click();
        //create contact
        WebElement createButton = driver.findElement(By.xpath("//*[@class='btn btn-primary btn-sm o-kanban-button-new']"));
        createButton.click();

        //Go to Opportunity Title and filled up
        WebElement OpportunityTitle = driver.findElement(By.xpath("//*[@class='o_field_char o_field_widget o_input o_required_modifier']"));
        OpportunityTitle.sendKeys("Opportunity");
        //Go to Customer

        WebElement selectElement = driver.findElement(By.xpath("//*[@class='o_field_widget o_field_many2one']"));

        selectElement.click();


        WebElement firstOption = driver.findElement(By.xpath("//ul/li[@class='ui-menu-item'][1]"));

        firstOption.click();

        WebElement revenue = driver.findElement(By.xpath("//*[@class='o_field_float o_field_number o_field_widget o_input']"));
        revenue.clear();
        revenue.sendKeys("500.00");


        WebElement CreateButton = driver.findElement(By.xpath("//*[@name='close_dialog']"));
        CreateButton.click();


    }

    @Test
    public void deleteOpportunity() {

        BriteERPUtil.login(driver, "eventscrmmanager38@info.com", "eventscrmmanager");

        //click CRM module
        driver.findElement(By.xpath("//span[@class='oe_menu_text'][contains(text(), 'CRM')]")).click();

        driver.findElement(By.xpath("//button[@type='button' and @class='btn btn-icon fa fa-lg fa-list-ul o_cp_switch_list']")).click();
        //make it pipeline
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.xpath("//li[.='Pipeline']")));


        //checkbox
        // driver.findElement(By.xpath("(//input[@type='checkbox'])[4]")).click();
        driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]")).click();
        //store how many created
        String numberOfPpl = driver.findElement(By.xpath("//span[@class='o_pager_limit']")).getText();
        int numberOfPeople = Integer.parseInt(numberOfPpl);
        //select action
        driver.findElement(By.xpath("//button[@class='o_dropdown_toggler_btn btn btn-sm dropdown-toggle'][contains(text(),'Action')]")).click();
        Sleep.sleep(5);

        //select delete
        driver.findElement(By.linkText("Delete")).click();
        Sleep.sleep(4);
        //select OK

        driver.findElement(By.xpath("//span[.='Ok']")).click();
        Sleep.sleep(5);
        //compare if the numbers are same(to find out if we deleted
        String numberOfPpl2 = driver.findElement(By.xpath("//span[@class='o_pager_limit']")).getText();
        Sleep.sleep(5);
        int numberOfPeople2 = Integer.parseInt(numberOfPpl2);
        Sleep.sleep(5);
        Assert.assertTrue(numberOfPeople > numberOfPeople2);

    }
}
