package pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage extends BasePage{

    public static final String PAGE_TITLE = "Sludinājumi - SS.LV";
    public static final String PAGE_URL = "https://ss.lv";

    @FindBy(xpath = "//a[@href='/ru/']") WebElement link_ToSwitchRu;
    @FindBy(xpath = "//a[@href='/lv/']") WebElement link_ToSwitchLv;
    @FindBy(xpath = "//h2/a[@href='/ru/electronics/']") WebElement button_MenuElectronics;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void clickRuLanguageLink(){
        link_ToSwitchRu.click();
        wait.until(ExpectedConditions.visibilityOf(link_ToSwitchLv));
    }
    public void clickMenuElectronics (){
        button_MenuElectronics.click();
    }








}
