package Practice2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AirbnbTest {

    String URL = "https://www.airbnb.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.out.println("Test has started");
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    @Test
    public void airbnbSearch() throws InterruptedException {
        driver.findElement(By.xpath("//*[@placeholder='Where are you going?']")).sendKeys("Budapest");
        // Use /div after the main xpath to find an element inside divs
        WebElement calendar = driver.findElement(By.xpath("//div[@data-testid='structured-search-input-field-split-dates-0']/div/div[2]"));
        calendar.click();

        Thread.sleep(2000);

        WebElement checkinDate = driver.findElement(By.xpath("//*[@data-testid='datepicker-day-2021-04-12']"));
        checkinDate.click();

        Thread.sleep(2000);

        WebElement checkoutDate = driver.findElement(By.xpath("//*[@data-testid='datepicker-day-2021-04-18']"));
        checkoutDate.click();

        WebElement guests = driver.findElement(By.xpath("//*[@data-testid='structured-search-input-field-guests-button']/div/div[2]"));
        guests.click();
        WebElement adultNumberGuests = driver.findElement(By.xpath("(//*[@aria-label='increase value'])[1]"));
        adultNumberGuests.click();
        adultNumberGuests.click();
        WebElement childNumberGuests = driver.findElement(By.xpath("(//*[@aria-label='increase value'])[2]"));
        childNumberGuests.click();
        WebElement infantNumberGuests = driver.findElement(By.xpath("(//*[@aria-label='increase value'])[3]"));
        infantNumberGuests.click();
        WebElement searchBtn = driver.findElement(By.xpath("//*[@data-testid='structured-search-input-search-button']"));
        searchBtn.click();

        List<WebElement> treeGuestsList = driver.findElements(By.xpath("//*[contains(text(), '3 guests')]"));
        Assert.assertNotEquals(0, treeGuestsList.size());
        boolean elementVisible = false;
        for (WebElement element: treeGuestsList){
            if (element.getText().contains("300+ stays")){
                elementVisible = true;
            }
        }

        Assert.assertTrue(elementVisible);

    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        System.out.println("A test run has finished...");
        Thread.sleep(30000);
        driver.close();
    }


}
