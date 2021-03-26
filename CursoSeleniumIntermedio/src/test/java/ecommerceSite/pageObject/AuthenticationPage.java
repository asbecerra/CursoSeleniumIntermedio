package ecommerceSite.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class AuthenticationPage {

    public WebDriver driver;
    public AuthenticationPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
    }
    public void validateAuthPage(){
        WebElement authenticationSection = driver.findElement(By.tagName("h1"));
        Assert.assertEquals("AUTHENTICATION", authenticationSection.getText());
        Assert.assertTrue(driver.getTitle().contains("Login"));
    }

    public CreateAccountPage createAccountEmail(String myEmail){
        driver.findElement(By.id("email_create")).sendKeys(myEmail);
        driver.findElement(By.id("SubmitCreate")).click();

        CreateAccountPage createAccountPage = CreateAccountPage(driver);
        return createAccountPage;
    }
}
