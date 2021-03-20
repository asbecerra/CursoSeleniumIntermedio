package ecommerceSite;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
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

    @FindBy(id ="id_gender2")
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

    @Test
    public void registrationTest() throws InterruptedException {
        Faker fake = new Faker();

        String fakeFirstName = fake.name().firstName();
        String fakeLastName = fake.name().lastName();
        //Set a random email
        String myEmail = "ansobete" + Math.random() + "@gmail.com";

        Utilities utilities = new Utilities(driver);
        utilities.registerAnUser(myEmail, fakeFirstName, fakeLastName);

        //WebElement myPersonalInformation = driver.findElement(By.xpath("//*[@href ='http://automationpractice.com/index.php?controller=identity']"));
        myPersonalInformation.click();

        //Validate personal information
        //WebElement firstNameTextField = driver.findElement(By.id("firstname"));
        //WebElement lastNameTextField = driver.findElement(By.id("lastname"));
        //WebElement emailTextField = driver.findElement(By.id("email"));

        String registeredName = firstNameTextField.getAttribute("value");
        String registeredLastName = lastNameTextField.getAttribute("value");
        String registeredEmail = emailTextField.getAttribute("value");

        Assert.assertEquals(fakeFirstName, registeredName);
        Assert.assertEquals(fakeLastName, registeredLastName);
        Assert.assertEquals(myEmail, registeredEmail);
    }

    @Test
    public void loginTest() throws InterruptedException {
        Faker fake = new Faker();

        String fakeFirstName = fake.name().firstName();
        String fakeLastName = fake.name().lastName();
        //Set a random email
        String myEmail = "ansobete" + Math.random() + "@gmail.com";

        Utilities utilities = new Utilities(driver);
        utilities.registerAnUser(myEmail, fakeFirstName, fakeLastName);
        //Logout
        //WebElement logoutBtn = driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?mylogout=']"));
        logoutBtn.click();
        //Login
        //WebElement emailLoginTextField = driver.findElement(By.id("email"));
        //WebElement pwdLoginTextField = driver.findElement(By.id("passwd"));

        emailLoginTextField.sendKeys(myEmail);
        pwdLoginTextField.sendKeys("Helloworld");
        //WebElement signInBtn = driver.findElement(By.id("SubmitLogin"));
        signInBtn.click();

        Assert.assertTrue(driver.getCurrentUrl().contains("my-account"));

        //WebElement userNameLogged = driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?controller=my-account']"));
        Assert.assertTrue(userNameLogged.getText().contains(fakeFirstName));
        Assert.assertTrue(userNameLogged.getText().contains(fakeLastName));

        //WebElement myAccountH1 = driver.findElement(By.tagName("h1"));
        Assert.assertEquals(myAccountH1.getText(), "MY ACCOUNT");
    }

    @Test
    public void checkUserMailIsUnique() throws InterruptedException {
        Faker fake = new Faker();

        String fakeFirstName = fake.name().firstName();
        String fakeLastName = fake.name().lastName();
        //Set a random email
        String myEmail = "ansobete" + Math.random() + "@gmail.com";

        Utilities utilities = new Utilities(driver);
        utilities.registerAnUser(myEmail, fakeFirstName, fakeLastName);
        //Logout
        //WebElement logoutBtn = driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?mylogout=']"));
        logoutBtn.click();
        //Register same email
        driver.findElement(By.id("email_create")).sendKeys(myEmail);
        driver.findElement(By.id("SubmitCreate")).click();

        //WebElement createErrorMsg = driver.findElement(By.xpath("//*[@id='create_account_error']/ol/li"));
        Assert.assertEquals(createErrorMsg.getText(), "An account using this email address has already been registered. Please enter a valid password or request a new one.");
    }
    public void registerAnUser (String myEmail,String fakeFirstName, String fakeLastName) throws InterruptedException {

        driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?controller=my-account']")).click();
        WebElement autenticationSection = driver.findElement(By.tagName("h1"));

        Assert.assertEquals("AUTHENTICATION", autenticationSection.getText());
        Assert.assertTrue(driver.getTitle().contains("Login"));

        driver.findElement(By.id("email_create")).sendKeys(myEmail);
        driver.findElement(By.id("SubmitCreate")).click();

        Thread.sleep(3000);
        //email validation
        WebElement emailValidation = driver.findElement(By.id("email"));
        String registerEmail = emailValidation.getAttribute("value");

        Assert.assertEquals(myEmail, registerEmail, "emails does not match");

        //WebElement femaleRadioButton = driver.findElement(By.id("id_gender2"));
        //WebElement firstName = driver.findElement(By.id("customer_firstname"));
        //WebElement lastName = driver.findElement(By.id("customer_lastname"));
        //WebElement password = driver.findElement(By.id("passwd"));
        //WebElement daySelector = driver.findElement(By.id("days"));
        //WebElement monthSelector = driver.findElement(By.id("months"));
        //WebElement yearSelector = driver.findElement(By.id("years"));
        //WebElement company = driver.findElement(By.id("company"));
        //WebElement address1 = driver.findElement(By.id("address1"));
        //WebElement address2 = driver.findElement(By.id("address2"));
        //WebElement city = driver.findElement(By.id("city"));
        //WebElement stateSelector = driver.findElement(By.id("id_state"));
        //WebElement postcode = driver.findElement(By.id("postcode"));
        //WebElement countrySelector = driver.findElement(By.id("id_country"));
        //WebElement additionalInfo = driver.findElement(By.id("other"));
        //WebElement phone = driver.findElement(By.id("phone"));
        //WebElement mobile = driver.findElement(By.id("phone_mobile"));
        //WebElement addressAlias = driver.findElement(By.id("alias"));
        //WebElement registerButton = driver.findElement(By.id("submitAccount"));

        femaleRadioButton.click();
        firstName.sendKeys(fakeFirstName);
        lastName.sendKeys(fakeLastName);
        password.sendKeys(Constants.PASSWORD);
        Select selectDays = new Select(daySelector);
        selectDays.selectByValue(Constants.BIRTH_DAY);
        Select selectMonths = new Select(monthSelector);
        selectMonths.selectByValue(Constants.BIRTH_MONTH);
        Select selectYears = new Select(yearSelector);
        selectYears.selectByValue(Constants.BIRTH_YEAR);
        company.sendKeys(Constants.COMPANY);
        address1.sendKeys(Constants.ADDRESS1);
        address2.sendKeys(Constants.ADDRESS2);
        city.sendKeys(Constants.CITY);
        Select selectState = new Select(stateSelector);
        selectState.selectByValue("32");
        postcode.sendKeys(Constants.POSTAL_CODE);
        Select selectCountry = new Select(countrySelector);
        selectCountry.selectByValue("21");
        additionalInfo.sendKeys(Constants.ADDITIONAL_INFO);
        phone.sendKeys(Constants.PHONE_NUMBER);
        mobile.sendKeys(Constants.MOBILE_NUMBER);
        addressAlias.clear();
        addressAlias.sendKeys(Constants.NICKNAME);

        //clicking on register button
        registerButton.click();

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        System.out.println("A test run has finished...");
        Thread.sleep(15000);
        driver.close();
    }

}
