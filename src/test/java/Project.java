import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class Project {
    public static void main(String[] args) throws InterruptedException {
        //1. Navigate to http://duotify.us-east-2.elasticbeanstalk.com/register.php

        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://duotify.us-east-2.elasticbeanstalk.com/register.php");

        //2. Verify  the title is "Welcome to Duotify!" (Use Assert class methods for all assertions)

        Assert.assertEquals("Welcome to Duotify!" , driver.getTitle());
        //3. Click on Signup here4. Fill out the form with the required info

        Faker faker = new Faker();
        driver.findElement(By.id("hideLogin")).click();

        String userName = faker.name().username();
        driver.findElement(By.id("username")).sendKeys(userName, Keys.ENTER);

        String firstName = faker.name().firstName();
        driver.findElement(By.id("firstName")).sendKeys(firstName, Keys.ENTER);

        String lastName = faker.name().lastName();
        driver.findElement(By.id("lastName")).sendKeys(lastName,Keys.ENTER);

        String email = faker.internet().emailAddress();
        driver.findElement(By.id("email")).sendKeys(email , Keys.ENTER);
        driver.findElement(By.id("email2")).sendKeys(email, Keys.ENTER);

        String password = faker.internet().password();
        driver.findElement(By.id("password")).sendKeys(password , Keys.ENTER);
        driver.findElement(By.id("password2")).sendKeys(password );

        //5. (You might want to use Faker class for that purpose since the data has to be different every time because the application does not allow you to sign up with the same username or email more than once)
        //6. Click on Sign up
        Thread.sleep(1000);
        driver.findElement(By.name("registerButton")).click();

        //7. Once logged in to the application, verify that the URL is:
        // http://duotify.us-east-2.elasticbeanstalk.com/browse.php?

        Assert.assertEquals(driver.getCurrentUrl(),"http://duotify.us-east-2.elasticbeanstalk.com/browse.php?");


        //8. In the left navigation bar, verify that your first and last name matches the first and last name that you used when signing up.

        Assert.assertEquals(firstName+" "+lastName ,( driver.findElement(By.id("nameFirstAndLast"))).getText());

        //9. Click on the username on the left navigation bar and verify the username on the main window is correct and then click logout.

        driver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);
        Assert.assertEquals( driver.findElement(By.className("userInfo")).getText(), (firstName+" "+lastName));
        driver.findElement(By.id("rafael")).click();


        //10. Verify that you are logged out by verifying the URL is:
        //http://duotify.us-east-2.elasticbeanstalk.com/register.php

        //Assert.assertEquals(driver.getCurrentUrl(),"http://duotify.us-east-2.elasticbeanstalk.com/register.php?" );

        //11 Login using the same username and password when you signed up.
        Thread.sleep(1000);
        driver.findElement(By.id("loginUsername")).sendKeys(userName, Keys.ENTER);
        driver.findElement(By.id("loginPassword")).sendKeys(password);
        driver.findElement(By.name("loginButton")).click();
        Thread.sleep(1000);
        //12. Verify successful login by verifying that the home page contains the text "You Might Also Like


        Assert.assertEquals( "You Might Also Like",( driver.findElement(By.className("pageHeadingBig"))).getText());

        //13. Log out once again and verify that you are logged out.

        driver.findElement(By.id("nameFirstAndLast")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("rafael")).click();
        Assert.assertEquals(driver.getCurrentUrl() , "http://duotify.us-east-2.elasticbeanstalk.com/settings.php?");

        driver.quit();
    }
}
