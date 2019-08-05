package test.page_object_model_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CRMPage;
import pages.LoginPage;
import pages.NavigateBar;
import test.TestBase;
import utilities.BrowserUtils;
import utilities.ConfigurationReader;

public class CRMTests extends TestBase {
    //WebDriver driver;   be careful! Don't write this since it cause error
    @BeforeMethod
    public void setUp() {
        driver.get(ConfigurationReader.get("url"));
        driver.manage().window().maximize();
    }
    //    User story: The system should display the correct information
//    for each opportunity on the view list page and the pivot table.
//
//    Acceptance Criteria:
//    1.Verify that second opportunity’ Expected Revenue value on the Pivot board
//    should be the same as the Expected revenue column value on the list board.
//    TEST CASE
//    Steps:
//        1.Login
//        2.Navigate to CRM page
//        3.Click the pivot button
//        4.Click the Total button to see the + sign
//        5.Click the Total button again to see the menu
//        6.Click the opportunity
//        7.Verify the Book Sale expected REvenueis $500.00
//        8.Click the List button
//        9.Verify the Book Sale expected REvenueis $500.00
//        10.Verify that second opportunity’ Expected Revenue value on the Pivot board
//        should be the same as the Expected revenue column value on the list board
    @Test
    public void secondOpportunity() {
//        1.Login
        String username = ConfigurationReader.get("CRM_user_username");
        String password = ConfigurationReader.get("CRM_user_password");
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
//        2.Navigate to CRM page
        NavigateBar navigateBar = new NavigateBar();
        navigateBar.navigateToMenu("CRM");
//        3.Click the pivot button
        CRMPage crmPage = new CRMPage();
        BrowserUtils.waitForClickablility(crmPage.pivotButton, 10);
        crmPage.pivotButton.click();
//        4.Click the Total button to see the + sign
        BrowserUtils.waitForClickablility(crmPage.totalButtonOpened, 10);
        crmPage.totalButtonOpened.click();
//        5.Click the Total button again to see the menu
//        BrowserUtils.waitForClickablility(crmPage.totalButton, 10);
        crmPage.getTotalButtonClosed.click();
//        6.Click the opportunity
        BrowserUtils.waitForClickablility(crmPage.opportunity, 10);
        crmPage.opportunity.click();
//        7.Verify the Book Sale expected REvenueis $500.00
        BrowserUtils.waitForVisibility(crmPage.tablePivotBookSale, 10);
        String revenuePivot = crmPage.tablePivotBookSale.getText();
        System.out.println("revenuePivot = " + revenuePivot);
//        8.Click the List button
        crmPage.listButton.click();
//        9.Verify the Book Sale expected REvenueis $500.00
        BrowserUtils.waitForVisibility(crmPage.tableListBookSale, 10);
        String revenueList = crmPage.tableListBookSale.getText();
        System.out.println("revenueList = " + revenueList);
//        10.Verify that second opportunity’ Expected Revenue value on the Pivot board
//        should be the same as the Expected revenue column value on the list board
        Assert.assertEquals(revenuePivot, revenueList);
    }













    //    Acceptance Criteria:
//
//            2. Verify that on the pivot table, the total expected revenue
//            should be the sum of all opportunities’ expected revenue.
    //    TEST CASE
//    Steps:
//        1.Login
//        2.Navigate to CRM page
//        3.Click the pivot button
//        4.Click the Total button to see the + sign
//        5.Click the Total button again to see the menu
//        6.Click the opportunity
//        7.Verify the Book Sale expected Revenue total price
//        8.Sum up the prices
//        9.Verify the total price and sum up prices are the same
    @Test
    public void sumofPivot() {
//        1.Login
        String username = ConfigurationReader.get("CRM_user_username");
        String password = ConfigurationReader.get("CRM_user_password");
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
//        2.Navigate to CRM page
        NavigateBar navigateBar = new NavigateBar();
        navigateBar.navigateToMenu("CRM");
//        3.Click the pivot button
        CRMPage crmPage = new CRMPage();
        BrowserUtils.waitForClickablility(crmPage.pivotButton, 10);
        crmPage.pivotButton.click();
//        4.Click the Total button to see the + sign
        BrowserUtils.waitForClickablility(crmPage.totalButtonOpened, 10);
        crmPage.totalButtonOpened.click();
//        5.Click the Total button again to see the menu
        BrowserUtils.waitForClickablility(crmPage.getTotalButtonClosed, 10);
        crmPage.getTotalButtonClosed.click();
//        6.Click the opportunity
        BrowserUtils.waitForClickablility(crmPage.opportunity, 10);
        crmPage.opportunity.click();
//        7.Verify the Book Sale expected REvenue tatol price
        BrowserUtils.waitForVisibility(crmPage.totalPrice, 10);
        String totalPriceStr = crmPage.totalPrice.getText();
        totalPriceStr = totalPriceStr.substring(0, 1) + totalPriceStr.substring(2);
        System.out.println("totalPriceStr = " + totalPriceStr);
        double totalPriceDouble = Double.parseDouble(totalPriceStr);
        System.out.println("totalPriceDouble = " + totalPriceDouble);
//        8.Sum up the prices

        double sumUpTotal = crmPage.sumOfRevenue();
        System.out.println("sumUpTotal = " + sumUpTotal);
//        9.Verify the total price and sum up prices are the same
        Assert.assertEquals(totalPriceDouble, sumUpTotal);
    }
}
