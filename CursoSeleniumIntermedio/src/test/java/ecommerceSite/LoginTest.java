package ecommerceSite;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    String URL = "http://www.automationpractice.com/index.php";
    public WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.out.println("Test has started");
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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

        WebElement myPersonalInformation = driver.findElement(By.xpath("//*[@href ='http://automationpractice.com/index.php?controller=identity']"));
        myPersonalInformation.click();

        //Validate personal information
        WebElement firstNameTextField = driver.findElement(By.id("firstname"));
        WebElement lastNameTextField = driver.findElement(By.id("lastname"));
        WebElement emailTextField = driver.findElement(By.id("email"));

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
        WebElement logoutBtn = driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?mylogout=']"));
        logoutBtn.click();
        //Login
        WebElement emailLoginTextField = driver.findElement(By.id("email"));
        WebElement pwdLoginTextFiel = driver.findElement(By.id("passwd"));

        emailLoginTextField.sendKeys(myEmail);
        pwdLoginTextFiel.sendKeys("Helloworld");
        WebElement signInBtn = driver.findElement(By.id("SubmitLogin"));
        signInBtn.click();

        Assert.assertTrue(driver.getCurrentUrl().contains("my-account"));

        WebElement userNameLogged = driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?controller=my-account']"));
        Assert.assertTrue(userNameLogged.getText().contains(fakeFirstName));
        Assert.assertTrue(userNameLogged.getText().contains(fakeLastName));

        WebElement myAccountH1 = driver.findElement(By.tagName("h1"));
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
        WebElement logoutBtn = driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?mylogout=']"));
        logoutBtn.click();
        //Register same email
        driver.findElement(By.id("email_create")).sendKeys(myEmail);
        driver.findElement(By.id("SubmitCreate")).click();

        WebElement createErrorMsg = driver.findElement(By.xpath("//*[@id='create_account_error']/ol/li"));
        Assert.assertEquals(createErrorMsg.getText(), "An account using this email address has already been registered. Please enter a valid password or request a new one.");
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        System.out.println("A test run has finished...");
        Thread.sleep(15000);
        driver.close();
    }
}
