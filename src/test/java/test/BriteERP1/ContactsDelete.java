package test.BriteERP1;

import utilities.BriteERPUtil;
import utilities.Sleep;
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

public class ContactsDelete {
   /*
        User Story:

        User should not be able to delete a  contact profile by clicking the "delete" button.

        test Steps:

        1) Navigate to the Application URL
        2) Click on "Contacts" link
        3) Click the first name of the company
        4) click the action button on the top
        5)click delete button
        6)Click Ok on the screen to delete
        7) Validation Error message needs to be show up


        54.148.96.210
        username: in_manuf_manager5@info.com
        password: kop98tfgQ72


          */

        WebDriver driver;

        @BeforeMethod
        public void setup(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();


        }
        @AfterMethod
        public void tearDown(){

            //     driver.quit();

        }

        @Test
        public void ContactsTC_43(){
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

            //login
            BriteERPUtil.login(driver, "in_manuf_manager5@info.com", "kop98tfgQ72");

        //        5) Click on ""Contacts"" link
            //tag[text()='LinkedText']

            driver.findElement(By.xpath("(//span[@class='oe_menu_text'][contains(text(), 'Contacts')])[1]")).click();
        //        6) Select first contact and click
        Sleep.sleep(2);
            driver.findElement(By.xpath("(//div[@class='o_kanban_image'])[1]")).click();
        //        7) Click actions
            Sleep.sleep(2);
            driver.findElement(By.xpath("(//button[@class='o_dropdown_toggler_btn btn btn-sm dropdown-toggle'])[3]")).click();


            //        Select delete and click ok bottom to delete selected contact"
           Sleep.sleep(2);
            driver.findElement(By.cssSelector("a[data-index='0'][data-section='other']")).click();
            driver.findElement(By.xpath("//span[.='Ok']")).click();
           // driver.findElement(By.xpath("//button[@class='btn btn-sm btn-primary']")).click();

            Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.xpath("//h4[@class='modal-title']")));


        }
}
