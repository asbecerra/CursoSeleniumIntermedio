package ecommerceSite.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {

    public WebDriver driver;
    public LandingPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
    }
}
