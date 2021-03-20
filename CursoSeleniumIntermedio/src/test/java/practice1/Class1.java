package practice1;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Class1 {
    String URL = "https://www.booking.com";
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        System.out.println("Test has started");
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
        driver.get(URL);
    }


    @Test
    public void firstTest(){
        System.out.println("-----> " + driver.getTitle());

        String style = driver.findElement(By.id("revc_write_a_review_login_intro")).getAttribute("style");
        System.out.println(style);
        //WebElement Hx = driver.findElement(By.className("qqqqq"));
        //List<WebElement> classQ = driver.findElements(By.className("qqqqq"));
        List<WebElement> invalidListTagname = driver.findElements(By.tagName("Tx"));
        List<WebElement> formsList = driver.findElements(By.xpath("/*[@name='form']"));

        Assert.assertNotEquals(invalidListTagname.size(), 0);
        Assert.assertFalse(invalidListTagname.isEmpty());
        Assert.assertNotEquals(formsList.size(), 0);

        for (WebElement element: invalidListTagname) {
            System.out.println(element.getText());
        }
    }

    @Test
    public void getAttribute(){
        driver.get("https://mundomac.com");
        List<WebElement> imagesList = driver.findElements(By.tagName("img"));
        Assert.assertNotEquals(imagesList.size(), 0);
        int i = 0;
        for (WebElement img: imagesList){
            String altAttr = img.getAttribute("alt");
            //System.out.println(altAttr);

            if (altAttr.isEmpty() || altAttr.equals("")){
                String imgSrc = img.getAttribute("src");
                System.out.println("***"+ imgSrc);
                i++;
            }
        }
        System.out.println("number of images without alt =  " + i);
    }

    @Test
    public void bbc(){
        driver.get("https://www.bbc.com");
        //WebElement divElement = driver.findElement(By.tagName("div"));
        List<WebElement> divElements = driver.findElements(By.tagName("div"));

        for (WebElement div: divElements){
            List<WebElement> paragraphs = div.findElements(By.tagName("p"));
            List<WebElement> imgs = div.findElements(By.tagName("img"));

            System.out.println("Img: " + imgs.size());

            if (paragraphs.size() >0) {
                System.out.println("The number of paragraphs is: " + paragraphs.size());
            }
        }
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        System.out.println("A test run has finished...");
        Thread.sleep(15000);
        driver.close();
    }
}
