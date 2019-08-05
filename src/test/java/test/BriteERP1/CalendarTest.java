package test.BriteERP1;

import utilities.BriteERPUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CalendarTest {

    /*
    Open the URL
    Enter valid user name
    Enter valid password
    Click sign in button
    Click "Calender" button on the top
    Click 23th of July, 2019
    Create an event and click edit on the event box
    Put the input "Demo Meeting", time etc
    Click "Save" button



     */
WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
//        driver.manage().window().maximize();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void closeDown() {
//driver.close();
    }


    @Test
    public void CalendarTC_44() {
// Enter valid user name
//    Enter valid password
//    Click sign in button

        BriteERPUtil.login(driver, "in_s_user4@info.com", "Wdf4ssa51");
       // Click "Calender" button on the top
    driver.findElement(By.xpath("(//span[@class='oe_menu_text'])[2]")).click();
         // Click 23th of July, 2019
driver.findElement(By.xpath("(//*[@class='ui-state-default'])[16]")).click();
//create an event at 11AM
        driver.findElement(By.xpath("//tr[@data-time='11:00:00'] //td[@class='fc-widget-content']"));
        //
      //  driver.findElement(By.xpath("//input[@class='o_input']")).sendKeys("Demo Meeting");
//
//        driver.findElement(By.xpath("//span[.='Create']")).click();
    }
}