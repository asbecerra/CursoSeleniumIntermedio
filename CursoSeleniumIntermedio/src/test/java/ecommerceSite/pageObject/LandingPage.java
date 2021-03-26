package ecommerceSite.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

    public WebDriver driver;
    public LandingPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
    }

    public void clickOnSingInBtn(){
        WebElement singInBtn = driver.findElement(By.xpath("//*[@href='http://automationpractice.com/index.php?controller=my-account']"));
        singInBtn.click();
    }
}
