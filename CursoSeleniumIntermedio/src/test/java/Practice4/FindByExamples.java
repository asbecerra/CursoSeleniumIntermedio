package Practice4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindByExamples {

    String URL = "https://www.netflix.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.out.println("Test has started");
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }
    @FindBy(tagName = "p")
    public List<WebElement> paragraphList;

    @FindBy(tagName = "a")
    public List<WebElement> linksList;

    @FindBy(tagName = "button")
    public List<WebElement> btnList;


    @Test
    public void exercise1(){
        //The following 3 lines were replaced by de FindBy
        //List<WebElement> paragraphList = driver.findElements(By.tagName("p"));
        //List<WebElement> linksList = driver.findElements(By.tagName("a"));
        //List<WebElement> bntList = driver.findElements(By.tagName("button"));

        Assert.assertNotEquals(paragraphList.size(), 0);
        Assert.assertNotEquals(linksList.size(), 0);
        Assert.assertNotEquals(btnList.size(), 0);
        System.out.println("# of paragrahs = " +paragraphList.size());
        System.out.println("# of Links = " +linksList.size());
        System.out.println("# of Buttons = " +btnList.size());

        for (WebElement link : linksList){
                if (link.getText().isEmpty() == false){
                    System.out.println("Link " + link.getText());
                }
        }
        System.out.println("Buttons List ");
        for (WebElement btn : btnList){
            if (btn.getText().isEmpty() == false){
                System.out.println("Button " + btn.getText());
            }
        }
    }
    @Test
    public void loginBtnSearch(){
        for(WebElement link : linksList){
            if(link.getText().equals("Login")){
                link.click();
            }
            //if (link.getText().contains("password")){
               // link.click();
           // }
        }
    }

}
