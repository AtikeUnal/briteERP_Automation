package pages;
import utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;
public class CRMPage {
    public CRMPage() {
        PageFactory.initElements(Driver.get(), this);
    }
    @FindBy(css = "button[data-original-title=Pivot]")
    public WebElement pivotButton;
    @FindBy(css = "button[data-original-title=List]")
    public WebElement listButton;
    @FindBy(xpath = "//tr//td[@class='o_pivot_header_cell_opened']")
    public WebElement totalButtonOpened;
    @FindBy(xpath = "//tr//td[@class='o_pivot_header_cell_closed']")
    public WebElement getTotalButtonClosed;
    @FindBy(xpath = "//a[.='Opportunity']")
    public WebElement opportunity;
    @FindBy(xpath = "(//tr//td[@class='o_pivot_cell_value text-right'])[5]")
    public WebElement tablePivotBookSale;
    @FindBy(xpath = "(//tr//td[@class='o_data_cell o_list_number'])[1]")
    public WebElement tableListBookSale;
    @FindBy(xpath = "(//tr//td[@class='o_pivot_cell_value text-right'])[1]")
    public WebElement totalPrice;
    @FindBy(xpath = "(//tr//td[@class='o_pivot_cell_value text-right'])[3]")
    public WebElement price1;
    @FindBy(xpath = "(//tr//td[@class='o_pivot_cell_value text-right'])[5]")
    public WebElement price2;
    @FindBy(xpath = "(//tr//td[@class='o_pivot_cell_value text-right'])[7]")
    public WebElement price3;
    //    @FindBy(xpath = "//tbody/tr/td[2]")
//    public List<WebElement> totalRevenue = new ArrayList<>();



    public double sumOfRevenue() {
        List<WebElement> totalRevenue = new ArrayList<>();
        totalRevenue = Driver.get().findElements(By.xpath("//tbody/tr/td[2]"));
        //  System.out.println(totalRevenue.toString());
        List<String > totalRevStr = new ArrayList<>();
        for (WebElement w : totalRevenue) {
            totalRevStr.add(w.getText());
        }
        List<Double> totalREvDouble = new ArrayList<>();
        for (String s : totalRevStr) {
            totalREvDouble.add(Double.parseDouble(s.replace(",", "")));
        }
        double sumOfActDouble = 0;
        int sumInt = 0;
        for (Double td:totalREvDouble) {
            sumOfActDouble += td;
        }
        return sumOfActDouble;
    }
//        public double sumOfExpectedRevenue(){
//            List<WebElement> priceList = Driver.get().findElements(By.xpath("//tbody/tr/td[2]"));
//            double sum=0;
//            for (int i =2; i<priceList.size(); i+=2){
//                sum+=Double.parseDouble(priceList.get(i).getText().replaceAll(“[,]“, “”));
//            }
//            sum=((int)(sum*100)/100.0);
//            return sum;
//        }
}