import ecommerceSite.Test.BaseTest;
import ecommerceSite.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Utilities extends BaseTest {

    WebDriver driver;

    //Constructor
    public Utilities(WebDriver remoteDriver){
        driver = remoteDriver;
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
}
