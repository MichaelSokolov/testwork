package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ElectronicsPage extends BasePage{

    @FindBy(xpath = "//a[@href = '/ru/electronics/search/']") WebElement button_Search;

    public ElectronicsPage(WebDriver driver) {
        super(driver);
    }

    public void clickSearchButton(){
        wait.until(ExpectedConditions.elementToBeClickable(button_Search));
        button_Search.click();

    }
}
