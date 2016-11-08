package lv.ss.test;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Logger;


public class SimpleTest {

    public WebDriver driver;
    MainPage mainpage;
    ElectronicsPage electronicspage;
    SearchPage searchpage;
    ResultsPage resultspage;
    FavoritesPage favoritespage;
    private static final Logger log = Logger.getLogger( SimpleTest.class.getName() );

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();

        mainpage = PageFactory.initElements(driver, MainPage.class);
        electronicspage = PageFactory.initElements(driver, ElectronicsPage.class);
        searchpage = PageFactory.initElements(driver, SearchPage.class);
        resultspage = PageFactory.initElements(driver, ResultsPage.class);
        favoritespage = PageFactory.initElements(driver, FavoritesPage.class);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            System.out.println(testResult.getStatus());
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("src/main/resources/drivers/testScreenShot.jpg"));
        }
    }

    @Test
    public void openMainPage()throws IOException{
        try{
        driver.get(mainpage.PAGE_URL);
        driver.manage().window().maximize();
        Assert.assertEquals(driver.getTitle(), mainpage.PAGE_TITLE, "Title of web page is wrong. Please check URL" );
        }catch(Exception e){
            Assert.fail();
        }
    }

    @Test(dependsOnMethods = "openMainPage")
    public void testCase()throws IOException{
        try{
            mainpage.clickRuLanguageLink();
            mainpage.clickMenuElectronics();
            electronicspage.clickSearchButton();
            //Select from dropdown elements by visible text
            //Inputs: Искомое слово или фраза; Цена: Мин; Цена: Макс; Подрубрика; Город\район; Искать за перод; Сортировать по;
            searchpage.fillSearshQuery("Computers", "0", "1000", "Разное", "Рига", "За последний месяц", "");
            //Select from dropdown by visible text
            //Inputs: Тип сделки;
            resultspage.changeCategory("Продажа");
            resultspage.sortByPrice();
            resultspage.openAdvancedSearch();
            //Select from dropdown elements by visible text
            //Inputs: Искомое слово или фраза; Цена: Мин; Цена: Макс; Подрубрика; Город\район; Искать за перод; Сортировать по;
            searchpage.fillSearshQuery("Computers", "160", "300", "Продают", "Все объявления", "Среди всех объявлений", "Цена");
            //Quantity of ADS should be selected randomly
            resultspage.selectSeveralAds(3);
            resultspage.clickAddToMemo();
            resultspage.acceptAllert();
            resultspage.clickOnFavorites();
            favoritespage.getIds();

            Assert.assertEquals(resultspage.arrayIds.size(), favoritespage.arrayIdsFav.size(), "Number of selected ads on results page and favorites page not equals");
            Collections.sort(resultspage.arrayIds);
            log.info("Id numbers of ads selected on result page: " + resultspage.arrayIds);
            Collections.sort(favoritespage.arrayIdsFav);
            log.info("Id numbers of ads added to favorite page:  " +favoritespage.arrayIdsFav);
            Assert.assertEquals(resultspage.arrayIds, favoritespage.arrayIdsFav, "Id's of selected ads on results page and favorites page not equals");

        }catch(Exception e){
            Assert.fail();
        }
    }
}

