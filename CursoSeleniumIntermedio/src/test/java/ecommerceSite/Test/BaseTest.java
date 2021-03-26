package ecommerceSite.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    String URL = "http://www.automationpractice.com/index.php";
    public WebDriver driver;

    @FindBy(id ="firstname")
    public WebElement firstNameTextField;

    @FindBy(id ="lastname")
    public WebElement lastNameTextField;

    @FindBy(id ="email")
    public WebElement emailTextField;

    @FindBy(xpath = "//*[@href ='http://automationpractice.com/index.php?controller=identity']")
    public WebElement myPersonalInformation;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?mylogout=']")
    public WebElement logoutBtn;

    @FindBy(id = "SubmitLogin")
    public WebElement signInBtn;

    @FindBy(id = "email")
    public WebElement emailLoginTextField;

    @FindBy(id = "passwd")
    public WebElement pwdLoginTextField;

    @FindBy(xpath = "//*[@href='http://automationpractice.com/index.php?controller=my-account']")
    public WebElement userNameLogged;

    @FindBy(tagName = "h1")
    public WebElement myAccountH1;

    @FindBy(xpath = "//*[@id='create_account_error']/ol/li")
    public WebElement createErrorMsg;

    @FindBy(id = "id_gender2")
    public WebElement femaleRadioButton;

    @FindBy(id = "customer_firstname")
    public WebElement firstName;

    @FindBy(id = "customer_lastname")
    public WebElement lastName;

    @FindBy(id = "passwd")
    public WebElement password;

    @FindBy(id = "days")
    public WebElement daySelector;

    @FindBy(id = "months")
    public WebElement monthSelector;

    @FindBy(id = "years")
    public WebElement yearSelector;

    @FindBy(id = "company")
    public WebElement company;

    @FindBy(id = "address1")
    public WebElement address1;

    @FindBy(id ="address2")
    public WebElement address2;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy(id = "id_state")
    public WebElement stateSelector;

    @FindBy(id = "postcode")
    public WebElement postcode;

    @FindBy(id = "id_country")
    public WebElement countrySelector;

    @FindBy(id = "other")
    public WebElement additionalInfo;

    @FindBy(id = "phone")
    public WebElement phone;

    @FindBy(id = "phone_mobile")
    public WebElement mobile;

    @FindBy(id = "alias")
    public WebElement addressAlias;

    @FindBy(id = "submitAccount")
    public WebElement registerButton;

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
    @AfterMethod
    public void tearDown() throws InterruptedException {
        System.out.println("A test run has finished...");
        Thread.sleep(15000);
        //driver.close();
    }
}
