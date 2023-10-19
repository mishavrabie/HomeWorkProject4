import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class project4 {

    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = null;
try {
    driver= new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

    // Navigate to Airbnb's homepage.

    driver.get("https://www.airbnb.com/");

    //  Click on the profile icon and 'Log in' link in the upper right corner (it's a link with a text 'Log in')
    driver.findElement(By.xpath("//button[@data-testid='cypress-headernav-profile']")).click();
    driver.findElement(By.linkText("Log in")).click();
    driver.findElement(By.xpath("//button[@data-testid='social-auth-button-email']")).click();
    //driver.findElement(By.xpath("//div[@class='fs7xov7 dir dir-ltr']")).click();
    //WebElement loginBtn = driver.findElement(By.xpath("//div[@class='fs7xov7 dir dir-ltr']"));
    //Thread.sleep(5000);
    //loginBtn.click();
    //Select dropDown = new Select(loginBtn);
    //dropDown.selectByIndex(0);

    //  Enter valid credentials (email and password) and log in
    //  Validate that the user's profile button with initial appears at the top right.


}finally {

}
    }
}
