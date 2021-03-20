package ecommerceSite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utilities {

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

        WebElement femaleRadioButton = driver.findElement(By.id("id_gender2"));
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        WebElement password = driver.findElement(By.id("passwd"));
        WebElement daySelector = driver.findElement(By.id("days"));
        WebElement monthSelector = driver.findElement(By.id("months"));
        WebElement yearSelector = driver.findElement(By.id("years"));
        WebElement company = driver.findElement(By.id("company"));
        WebElement address1 = driver.findElement(By.id("address1"));
        WebElement address2 = driver.findElement(By.id("address2"));
        WebElement city = driver.findElement(By.id("city"));
        WebElement stateSelector = driver.findElement(By.id("id_state"));
        WebElement postcode = driver.findElement(By.id("postcode"));
        WebElement countrySelector = driver.findElement(By.id("id_country"));
        WebElement additionalInfo = driver.findElement(By.id("other"));
        WebElement phone = driver.findElement(By.id("phone"));
        WebElement mobile = driver.findElement(By.id("phone_mobile"));
        WebElement addressAlias = driver.findElement(By.id("alias"));
        WebElement registerButton = driver.findElement(By.id("submitAccount"));

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
