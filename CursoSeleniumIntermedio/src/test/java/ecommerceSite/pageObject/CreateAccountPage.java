package ecommerceSite.pageObject;

import ecommerceSite.Constants;
import ecommerceSite.Test.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CreateAccountPage extends BaseTest {

    public WebDriver driver;
    public CreateAccountPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
    }


    public String confirmRegisterEmail(){
        WebElement emailValidation = driver.findElement(By.id("email"));
        String registerEmail = emailValidation.getAttribute("value");
        return registerEmail;
    }

    public MyAccountPage fillingRegisterForm(String fakeFirstName, String fakeLastName) throws InterruptedException {
        Thread.sleep(5000);

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
        Thread.sleep(2000);

        //clicking on register button
        registerButton.click();
        Thread.sleep(15000);

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        return myAccountPage;
    }
}
