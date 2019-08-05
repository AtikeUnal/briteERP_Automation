package test.BriteERP1;

import utilities.BriteERPUtil;
import utilities.Sleep;
import utilities.VerificationUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Test2 {


    /*

    BriteERP results Search Functionality test Search  Company Name for
                                          test Search  Name for
                                          test Search  Tag for
                                          test Search  SalesPerson for
    1-Open browser
    2-Go to http://54.148.96.210/web/login
    3- Login as an Inventory manager
    4 Verify name of the Inventory Manager is displayed on top right
    5-Go to "Contacts" Module and  click
    6-Verify page name
    7-Go to Search box
    8-Search for Company Names
    Credential for existing companies: --> ("ABC Company", "ACD (copy)")
    9-Save the total number of results
    10-In the results pages,
    Verify that search result number is equal to related Company Name search result
    Click on the first result
    Verify that  on first result page total number is equal to  search result total value
    Print  for each company's total search results
    *According to credential result cannot be 0(Empty) ** If total =0 there is a defect!!!*
    Repeat the same steps for second company name search items in the list


     */





        WebDriver driver;

        @BeforeMethod
        public void before() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("http://54.148.96.210/web/login");
            driver.manage().window().maximize();

        }

        @Test(description = "Login as an Inventory Manager",priority = 1)
        public void loginPositive() throws InterruptedException {
            //Login as an Inventory Manager
            BriteERPUtil.login(driver, "in_4@info.com", "alsfuh7we74");

            //  Verify name of the Inventory Manager is displayed on top right
            String managerName = "//*[@class='oe_topbar_name']";
            Assert.assertTrue(VerificationUtils.isElementDisplayed(driver, By.xpath(managerName)));
            Sleep.sleep(2);
        }
        @Test(description = "Click on Contacts Module",priority = 2)

        public void ClickContacts() throws InterruptedException {
            loginPositive();
            // Click on  Contacts Module
            WebElement ContactModule = driver.findElement(By.xpath("//*[@data-menu-xmlid='contacts.menu_contacts']"));
            ContactModule.click();


            //verify page name
            String expected = driver.findElement(By.xpath("//*[@class='active']")).getText();
            String actual = "Contacts";
            Thread.sleep(2000);
            Assert.assertEquals(expected, actual);
        }

        @Test(description = "Go to Search Box,- Search Company Name for",priority = 3)
        public void SearchBox() throws InterruptedException {

            ClickContacts();


            List<String> searchCompanyNames = Arrays.asList("ABC Company", "ACD (copy)");

            for (int i = 0; i < searchCompanyNames.size(); i++) {
                WebElement SearchElement = driver.findElement(By.xpath("//*[@class='o_searchview_input']")); //search
                SearchElement.sendKeys(searchCompanyNames.get(i) + Keys.ARROW_DOWN + Keys.ARROW_DOWN + Keys.ARROW_DOWN + Keys.ARROW_DOWN + Keys.ENTER + Keys.ARROW_DOWN + Keys.ENTER);

                Sleep.sleep(2);
                String total = driver.findElement(By.xpath("//*[@class='o_pager_limit']")).getText();

                WebElement totalOnContactpage = driver.findElement(By.xpath("//*[@class='oe_kanban_details'][1]"));
                totalOnContactpage.click();
                Sleep.sleep(2);
                String totalOnContactPage = driver.findElement(By.xpath("//*[@class='o_pager_limit']")).getText();


                String expected = total;
                String actual = totalOnContactPage;
                Thread.sleep(2000);
                Assert.assertEquals(expected, actual);

                System.out.println(searchCompanyNames.get(i)+" Company Total: " + total);
                Sleep.sleep(2);

                // Go bact to  Contacts Module to continue...
                WebElement ContactModule = driver.findElement(By.xpath("//*[@data-menu-xmlid='contacts.menu_contacts']"));
                ContactModule.click();
                Sleep.sleep(3);


            }

        }



        @Test(description = " Search Name for", priority = 4)


        public void SearchByPersonName() throws InterruptedException {
            ClickContacts();
            List<String> searchPersonNames = Arrays.asList("John", "Maria");

            for (int i = 0; i < searchPersonNames.size(); i++) {
                WebElement SearchElement = driver.findElement(By.xpath("//*[@class='o_searchview_input']")); //search
                SearchElement.sendKeys(searchPersonNames.get(i)  +Keys.ENTER);

                Sleep.sleep(2);
                String total = driver.findElement(By.xpath("//*[@class='o_pager_limit']")).getText();

                System.out.println(searchPersonNames.get(i)+" Name Total: " + total);
                Sleep.sleep(2);

                WebElement clearSearch = driver.findElement(By.xpath("//*[@class='fa fa-sm fa-remove o_facet_remove'] "));
                clearSearch.click();

            }

        }
        @Test(description = "Search  Tag for", priority = 5)
        public void SearchByTag() throws InterruptedException {
            ClickContacts();
            List<String> searchPersonNames = Arrays.asList("abc", "bbb","ccc");

            for (int i = 0; i < searchPersonNames.size(); i++) {
                WebElement SearchElement = driver.findElement(By.xpath("//*[@class='o_searchview_input']")); //search
                SearchElement.sendKeys(searchPersonNames.get(i) +Keys.ARROW_DOWN + Keys.ENTER);

                Sleep.sleep(2);
                String total = driver.findElement(By.xpath("//*[@class='o_pager_limit']")).getText();

                System.out.println(searchPersonNames.get(i)+" TagName Total: " + total);
                Sleep.sleep(2);

                WebElement clearSearch = driver.findElement(By.xpath("//*[@class='fa fa-sm fa-remove o_facet_remove'] "));
                clearSearch.click();

            }

        }
        @Test(description ="Search SalesPerson for" ,priority=6 )
        public void SearchBySalesPerson() throws InterruptedException {
            ClickContacts();
            List<String> searchPersonNames = Arrays.asList("ABC Company", "G&K");

            for (int i = 0; i < searchPersonNames.size(); i++) {
                WebElement SearchElement = driver.findElement(By.xpath("//*[@class='o_searchview_input']")); //search
                SearchElement.sendKeys(searchPersonNames.get(i) +Keys.ARROW_DOWN  +Keys.ARROW_DOWN  +Keys.ENTER+Keys.ARROW_DOWN+Keys.ENTER);

                Sleep.sleep(2);
                String total = driver.findElement(By.xpath("//*[@class='o_pager_limit']")).getText();

                if(total.equals("")){
                    System.out.println("Fail");
                }else{
                    System.out.println("Pass");
                }
                System.out.println(searchPersonNames.get(i)+" Sales Person Total: " + total);
                Sleep.sleep(2);


                WebElement clearSearch = driver.findElement(By.xpath("//*[@class='fa fa-sm fa-remove o_facet_remove'] "));
                clearSearch.click();



            }

        }


    }