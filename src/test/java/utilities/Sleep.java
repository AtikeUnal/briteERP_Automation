package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

public class Sleep {

    public static void sleep(int a) {
        try {
            Thread.sleep(a * 1000);
        } catch (Exception e) {

        }
    }


    //this method will make selenium wait within time for element to be displayed.
    public static void waitForElementToBeDisplayed(int time, WebElement element){

       int i=0;
       while(i < time) {

           try {
               element.isDisplayed();
               break;
           } catch (WebDriverException e) {
               System.out.println(e.getMessage());
               System.out.println("Attemt : "+i);
            i++;
            sleep(1);
           }
       }
    }

    /**
     *
     * @param time
     * @param by
     * @param driver
     *  This method will make selenium wait within time for element to be displayed.
     */
    public static void sleep(int time, By by, WebDriver driver) {
        int i = 0;
        while (i < time) {
            try {
                driver.findElement(by).isDisplayed();
                break;
            } catch (WebDriverException e) {
                System.out.println(e.getMessage());
                System.out.println("Attempt : "+i);
                i++;
                sleep(1);
            }
        }
    }
}