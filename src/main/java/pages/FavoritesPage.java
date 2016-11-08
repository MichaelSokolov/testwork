package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class FavoritesPage extends BasePage{

    public ArrayList<String> arrayIdsFav = new ArrayList<String>();

    public FavoritesPage(WebDriver driver) {
        super(driver);
    }

    @FindAll({@FindBy(xpath = "//td[@class='msga2 pp0']/input")})
    private List<WebElement> listOfAds;


    public ArrayList<String> getIds (){
        wait.until(ExpectedConditions.elementToBeClickable(listOfAds.get(1)));
        for(int i=0; i<listOfAds.size(); i++){
            String id = listOfAds.get(i).getAttribute("id");
            arrayIdsFav.add(id);
        }
        return arrayIdsFav;
    }


}
