package ecommerceSite;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest{

    @Test
    public void registrationTest () throws InterruptedException {
        Faker fake = new Faker();

        String fakeFirstName = fake.name().firstName();
        String fakeLastName = fake.name().lastName();
        //Set a random email
        String myEmail = "ansobete" + Math.random() + "@gmail.com";
        registerAnUser(myEmail, fakeFirstName, fakeLastName);

        myPersonalInformation.click();

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
        registerAnUser(myEmail, fakeFirstName, fakeLastName);
        //Logout
        logoutBtn.click();

        //Login
        emailLoginTextField.sendKeys(myEmail);
        pwdLoginTextField.sendKeys("Helloworld");
        signInBtn.click();

        Assert.assertTrue(driver.getCurrentUrl().contains("my-account"));
        Assert.assertTrue(userNameLogged.getText().contains(fakeFirstName));
        Assert.assertTrue(userNameLogged.getText().contains(fakeLastName));
        Assert.assertEquals(myAccountH1.getText(), "MY ACCOUNT");
    }

    @Test
    public void checkUserMailIsUnique() throws InterruptedException {
        Faker fake = new Faker();

        String fakeFirstName = fake.name().firstName();
        String fakeLastName = fake.name().lastName();
        //Set a random email
        String myEmail = "ansobete" + Math.random() + "@gmail.com";
        
        registerAnUser(myEmail, fakeFirstName, fakeLastName);
        //Logout
        logoutBtn.click();
        //Register same email
        driver.findElement(By.id("email_create")).sendKeys(myEmail);
        driver.findElement(By.id("SubmitCreate")).click();
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
