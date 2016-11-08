package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class SearchPage extends BasePage{

    @FindBy(id = "ptxt") WebElement input_SearchRequest;
    @FindBy(id = "cmp_1") WebElement form_FirstSuggestions;
    @FindBy(xpath = "//input[contains(@name, 'min')]") WebElement input_MinPrice;
    @FindBy(xpath = "//input[contains(@name, 'max')]") WebElement input_MaxPrice;
    @FindBy(xpath = "//select[@name='sid']") WebElement dropdown_SubSection;
    @FindBy(id = "s_region_select") WebElement dropdown_Region;
    @FindBy(xpath = "//select[@name='pr']") WebElement dropdown_Period;
    @FindBy(xpath = "//select[@name='sort']") WebElement dropdown_Sort;
    @FindBy(id = "sbtn") WebElement button_Submit;

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void enterSearchrequest(String request){
        wait.until(ExpectedConditions.elementToBeClickable(input_SearchRequest));
        input_SearchRequest.clear();
        input_SearchRequest.sendKeys(request);
        Assert.assertEquals(input_SearchRequest.getAttribute("value"), request);
    }

    public void enterMinPrice(String minPrice){
        input_MinPrice.clear();
        input_MinPrice.sendKeys(minPrice);
        Assert.assertEquals(input_MinPrice.getAttribute("value"), minPrice);
    }

    public void enterMaxPrice (String maxPrice){
        input_MaxPrice.clear();
        input_MaxPrice.sendKeys(maxPrice);
        Assert.assertEquals(input_MaxPrice.getAttribute("value"), maxPrice);
    }

    public void selectSubSection(String value) {
        Select select = new Select(dropdown_SubSection);
        select.selectByVisibleText(value);
    }

    public void selectRegion(String value){
        Select select = new Select(dropdown_Region);
        select.selectByVisibleText(value);
    }

    public void selectPeriod(String value){
        Select select = new Select(dropdown_Period);
        select.selectByVisibleText(value);
    }

    public void selectSort(String value){
        Select select = new Select(dropdown_Sort);
        select.selectByVisibleText(value);
    }

    public void pressSubmitButton(){
        button_Submit.click();
    }

    public void fillSearshQuery(String search, String minPrice, String maxPrice, String section, String region, String period, String sort){
        enterSearchrequest(search);
        enterMinPrice(minPrice);
        enterMaxPrice(maxPrice);
        selectSubSection(section);
        selectRegion(region);
        selectPeriod(period);
        selectSort(sort);
        pressSubmitButton();
    }


}
