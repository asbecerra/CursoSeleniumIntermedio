package ecommerceSite.pageObject;

import org.openqa.selenium.WebDriver;

public class AuthenticationPage {

    public WebDriver driver;
    public AuthenticationPage(WebDriver remoteDriver){
        this.driver = remoteDriver;
    }
}
