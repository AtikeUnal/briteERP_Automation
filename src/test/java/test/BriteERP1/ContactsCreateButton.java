package test.BriteERP1;

import utilities.BriteERPUtil;
import utilities.VerificationUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactsCreateButton {
    /*
     "1) Navigate to the Application URL
 2)Enter username
 3) Enter password
 4) Click sign in button
 5) Click on ""Contacts"" link
 6) Select contact name ""Aiman
 7) Click actions
 8) Select delete and click ok botton to delete selected contact"
 """54.148.96.210
 username: in_manuf_manager5@info.com
 password: kop98tfgQ72""
 Contact name: Aiman"
      */

    WebDriver driver;

    /*
        User Story:

        User should be able to create a new contact profile by clicking the "create" button.

        test Steps:

        1) Navigate to the Application URL
        2) Click on "Contacts" link
        3) Click the "Create" button
        4) Fill out contact's name as "Valve" and click "Save"

        54.148.96.210
        username: in_manuf_manager5@info.com
        password: kop98tfgQ72
        new contact name: Valve

          */
    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @AfterMethod
    public void closeDown() {
        driver.close();
    }


    @Test
    public void ContactsTC_27() {
        //"1) Navigate to the Application URL
        BriteERPUtil.login(driver, "in_manuf_manager5@info.com", "kop98tfgQ72");

        //2) Click on ""Contacts"" link

        driver.findElement(By.xpath("(//span[@class='oe_menu_text'][contains(text(), 'Contacts')])[1]")).click();

        //3) Click the ""Create"" button
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm o-kanban-button-new btn-default']")).click();
        //4) Fill out contact's name as ""Valve"" and click ""Save"""
        driver.findElement(By.xpath("//input[@class='o_field_char o_field_widget o_input o_required_modifier']")).sendKeys("Valve");
        //4) Fill out contact's name as ""Valve"" and click ""save"""
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm o_form_button_save']")).click();
        Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.linkText("Contacts")));

    }
}

/*

Language Java, HTML, CSS
Testing tool Selenium WebDriver, Maven, TestNG
Project management/ bug track tool Jira, Jira X-ray
Version control systemGit
Build management tool Maven
 IDE  IntelliJ
Browsers Google Chrome,
 Project Methodologies Agile-Scrum, Waterfall
 */