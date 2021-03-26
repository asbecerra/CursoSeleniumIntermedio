package ecommerceSite.Test;

import com.github.javafaker.Faker;
import ecommerceSite.Constants;
import ecommerceSite.pageObject.AuthenticationPage;
import ecommerceSite.pageObject.CreateAccountPage;
import ecommerceSite.pageObject.LandingPage;
import ecommerceSite.pageObject.MyAccountPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

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
        MyAccountPage myAccountPage = registerAnUser(myEmail, fakeFirstName, fakeLastName);

        Thread.sleep(5000);
        //Logout
        logoutBtn.click();

        //Login
        emailLoginTextField.sendKeys(myEmail);
        pwdLoginTextField.sendKeys("Helloworld");
        Thread.sleep(5000);
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

    private MyAccountPage registerAnUser (String myEmail, String fakeFirstName, String fakeLastName) throws InterruptedException {

        LandingPage landingPage = new LandingPage(driver);
        //como es un flujo, la pagina de Authentication se presenta despues de la landingPage
        AuthenticationPage authenticationPage = landingPage.clickOnSingInBtn();
        authenticationPage.validateAuthPage();
        CreateAccountPage createAccountPage = authenticationPage.createAccountEmail(myEmail);

        Thread.sleep(5000);
        //email validation
        String registerEmail = createAccountPage.confirmRegisterEmail();
        Assert.assertEquals(registerEmail, myEmail, "emails does not match");

        return createAccountPage.fillingRegisterForm(fakeFirstName, fakeLastName);
    }
}
