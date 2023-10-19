import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

import java.time.Duration;

public class projectTwo {
    public static void main(String[] args) throws InterruptedException {

       //**Song Search on Spotify Web Player**

       //Preconditions:

        //The tester has a valid Spotify account. (Create a spotify account with a valid but not personal email)
        //The tester is logged out of the Spotify Web Player at the start.


        //1. Login:

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2)); // set implicit wait

        //(You can't inspect the html in this app directly through the homepage by right click and inspect, so make sure the inspect/developer window is open before you navigate to the homepage)

        //Navigate to Spotify's Web Player homepage. (https://open.spotify.com/)

        driver.get("https://open.spotify.com/");

        //Click on the 'Log In' link.

        driver.findElement(By.xpath("//span[.='Log in']")).click();
        Thread.sleep(2000);

                //Enter valid credentials (email and password).

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("m93497889@gmail.com", Keys.TAB ,"qa12345!" );
        //driver.findElement(By.xpath("//input[@type='password'")).sendKeys("msp2023!");

        //Click the 'Log In' button.

         driver.findElement(By.xpath("//span[.='Log In']")).click();

        //Validate that the profile icon is displayed at the top right, indicating a successful login. (it is a <figure> element)
        Thread.sleep(13000);
        WebElement cofirmButon = driver.findElement(By.xpath("//div[@class='KdxlBanhDJjzmHfqhP0X Q4hOdDPRDHPhfou2xbDg m95Ymx847hCaxHjmyXKX']"));

        Assert.assertTrue(cofirmButon.isDisplayed());
        //2. Music Search:

        //Click on Search link on the left bar and Use the search bar at the top to search for a specific artist and song, e.g., "Adele Hello".

        Thread.sleep(10000);
        driver.findElement(By.xpath("//a[@aria-label='Search']")).click();
        Thread.sleep(5000);

        //From the search results, play the relevant song (Hello by Adele) by clicking on the play icon

        driver.findElement(By.xpath("//input[@maxlength='800']")).sendKeys("Adele Hello", Keys.ENTER);
        Thread.sleep(5000);
        driver.findElement(By.xpath("//img[@ class='mMx2LUixlnN_Fu45JpFB rkw8BWQi3miXqtlJhKg0 Yn2Ei5QZn19gria6LjZj']")).click();
        //Once song is playing, validate the play functionality by verifying the song name (Hello) and artist (Adele) in the now-playing section at the left bottom corner.

        String song =driver.findElement(By.xpath("//a[@data-testid='context-item-link']")).getText();
        Assert.assertTrue(song.equals("Hello"));
        String name = driver.findElement(By.xpath("//a[@data-testid='context-item-info-artist']")).getText();
        Assert.assertTrue(name.equals("Adele"));
        //3. Logout:

        //Click on the profile icon  at the top right to access the account dropdown.

        Thread.sleep(3000);
        driver.findElement(By.xpath("//div[@class='KdxlBanhDJjzmHfqhP0X Q4hOdDPRDHPhfou2xbDg m95Ymx847hCaxHjmyXKX']")).click();
        Thread.sleep(2000);

        //Click 'Log out'.

        driver.findElement(By.xpath("//span[.='Log out']"));
        //Validate the user has been logged out by ensuring the 'Log In' button is present.
        WebElement confirmLogOut = driver.findElement(By.xpath("//span[.='Log In']"));
        Assert.assertTrue(confirmLogOut.isDisplayed());

        //Suggestions:
        //- Maximize your window before test starts
        //- Use Thread.sleep() when needed, to synchronize your automation script with the web events.
        //- Alternatively, you can use implicit wait of 5 seconds for every locator which will wait up to 5 seconds if the element is not found right away.
        //It should be set once before test starts
        //Syntax:
        //WebDriver driver = new ChromeDriver();
        //driver.manage().window().maximize(); // maximize
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); // set implicit wait

        //- Try using xpath for all locators


    }
}
