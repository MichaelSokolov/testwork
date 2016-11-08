package pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static org.bitbucket.dollar.Dollar.$;


public class ResultsPage extends BasePage{

    private static final Logger log = Logger.getLogger( ResultsPage.class.getName() );
    private   String idOfProduct;
    public ArrayList<String> arrayIds = new ArrayList<String>();
    private List<Integer> list;

    public ResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//select[@class='filter_sel']") WebElement dropdown_Category;
    @FindBy(xpath = "//a[@class='a19']") WebElement link_toSortByPrice;
    @FindBy(xpath = "//a[@class='a9a']") WebElement link_AdvancedSearch;
    @FindBy(id = "a_fav_sel") WebElement link_addToMemo;
    @FindBy(id = "alert_ok") WebElement buttonAlertOk;
    @FindBy(xpath = "//a[@href='/ru/favorites/']") WebElement link_Favorites;
    @FindAll({@FindBy(xpath = "//td[@class='msga2 pp0']/input")})
    private List<WebElement> listOfAds;


    public void sortByPrice(){
        wait.until(ExpectedConditions.elementToBeClickable(link_toSortByPrice));
        link_toSortByPrice.click();
    }

    public void changeCategory( String value){
        wait.until(ExpectedConditions.elementToBeClickable(dropdown_Category));
        Select select = new Select(dropdown_Category);
        select.selectByVisibleText(value);
    }

    public void openAdvancedSearch(){
        wait.until(ExpectedConditions.elementToBeClickable(link_AdvancedSearch));
        link_AdvancedSearch.click();
    }

    public List<Integer> createList(){
        list = $(0, listOfAds.size()).toList();
        return list;
    }

    public String selectRundomAds() throws Exception{
        Random randomGenerator = new Random();
        int i = randomGenerator.nextInt(list.size());
        listOfAds.get(list.get(i)).click();
        idOfProduct = listOfAds.get(list.get(i)).getAttribute("id");
        list.remove(i);
        Thread.sleep(1000);
        return idOfProduct;
    }

    public ArrayList<String> selectSeveralAds (int x) throws Exception{
        if (x > listOfAds.size() || x < 0 || x == 0) {
            log.warning("Value of selecting several ads: "+x+" is wrong,- it bigger when found ads: " + listOfAds.size() + " or value is negative or zero");
            Assert.fail();
        }
        createList();
        for(int z=0; z<x; z++){
            selectRundomAds();
            arrayIds.add(idOfProduct);
        }
        return arrayIds;
   }

    public void clickAddToMemo(){
        link_addToMemo.click();
    }

    public void acceptAllert(){
        wait.until(ExpectedConditions.elementToBeClickable(buttonAlertOk));
        buttonAlertOk.click();
    }

    public void clickOnFavorites(){
        wait.until(ExpectedConditions.elementToBeClickable(link_Favorites));
        link_Favorites.click();
    }

}