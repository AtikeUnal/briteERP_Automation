package utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;



public class BriteERPUtilities {






    public static void login(WebDriver driver, String username, String password){

        driver.get("http://54.148.96.210/");
        driver.findElement(By.xpath("//b[contains(text(), 'Sign in')]")).click();
        driver.findElement(By.cssSelector("input[id='login']")).sendKeys(username);
        Sleep.sleep(2);

        driver.findElement(By.cssSelector("input[id='password'")).sendKeys(password+ Keys.ENTER);
        driver.manage().window().maximize();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void selectMenuOption(WebDriver driver, String tab, String module) throws InterruptedException {
        // click on tab
        String tabXpath = "//span[@class='title title-level-1' and contains(text(), '"+tab+"')]";
        driver.findElement(By.xpath(tabXpath)).click();
        Thread.sleep(1000);
        // click on module
        String moduleXpath = "//span[@class='title title-level-2' and contains(text(), '"+module+"')]";
        driver.findElement(By.xpath(moduleXpath)).click();
        Thread.sleep(2000);
    }
}