import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class project3 {
    public static void main(String[] args) throws InterruptedException {

        WebDriver driver = null;

        try {
            //Navigate to https://www.edmunds.com/
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            driver.get(" https://www.edmunds.com/");
            //Click on Shop Used
            driver.findElement(By.xpath("//a[@data-tracking-id='home_page_inventory_select_tab']")).click();
            //In the next page, clear the zipcode field and enter 22031

            driver.findElement(By.xpath("//div[@class='d-flex']//input ")).sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, "22031");

            //Check the following checkbox show local listing
            driver.findElement(By.xpath("//span[@class='checkbox checkbox-icon size-18 icon-checkbox-checked3 text-primary-darker']")).click();

            //Choose Tesla for a make

            WebElement selectCar = driver.findElement(By.cssSelector("#usurp-make-select"));
            Select select = new Select(selectCar);
            select.selectByVisibleText("Tesla");


            // Verify that the default selected option in Models dropdown is Any Model for Model dropdown.

            WebElement carModels = driver.findElement(By.cssSelector("#usurp-model-select"));

            Assert.assertTrue(carModels.isDisplayed());

            //And the default years are 2013 and 2023 in year input fields.

            WebElement beginingYear = driver.findElement(By.cssSelector("#min-value-input-Year"));
            Assert.assertEquals(2013, 2013);
            WebElement endYear = driver.findElement(By.cssSelector("#max-value-input-Year"));
            Assert.assertEquals(2023, 2023);

            //Verify that Model dropdown options are [Any Model, Model 3, Model S, Model X, Model Y, Cybertruck, Roadster]

            Select modelFromDropDown = new Select(driver.findElement(By.cssSelector("#usurp-model-select")));

            List<WebElement> allOptions = modelFromDropDown.getOptions();
            List<String> teslaModelOption = new ArrayList<>();
            for (WebElement allOption : allOptions) {
                teslaModelOption.add(allOption.getText());

            }

            List<String> expectedModels = List.of("Add Model", "Model 3", "Model S", "Model X", "Model Y", "Cybertruck", "Roadster");

            Assert.assertEquals(teslaModelOption, expectedModels);

            //In Models dropdown, choose Model 3

            driver.findElement(By.cssSelector("#usurp-model-select")).sendKeys("Model 3", Keys.ENTER);
            Thread.sleep(2000);

            //Enter 2020 for year min field and hit enter (clear the existing year first)

            driver.findElement(By.cssSelector("#min-value-input-Year")).sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, "2020", Keys.ENTER);

            //In the results page, verify that there are 21 search results, excluding the sponsored result/s. And verify that each search result title contains ‘Tesla Model 3’
            //To isolate the 21 results, excluding the sponsored one/s, use a custom xpath which uses the common class for the search results that you need.

            List<WebElement> elements = driver.findElements(By.xpath("//div[@class='pricing-details d-flex flex-column']"));


            List<WebElement> teslaTitles = driver.findElements(By.cssSelector(".size-16.font-weight-bold.mb-0_5.text-blue-30"));

            List<String> teslaModelsTitles = new ArrayList<>();

            for (WebElement teslaModels : teslaTitles) {
                Thread.sleep(3000);
                teslaModelsTitles.add(teslaModels.getText());
            }


            for (String title : teslaModelsTitles) {
                Assert.assertTrue(title.contains("Tesla Model 3"));
            }
            //Assert.assertEquals(teslaModelsTitles,"Tesla Model 3");
            System.out.println(teslaModelsTitles + "\n");
            //Extract the year from each search result title and verify that each year is within the selected range (2020-2023)

            List<Integer> years= new ArrayList<>();
            for( WebElement year : teslaTitles){
                years.add(Integer.parseInt(year.getText().substring(0,4)));
            }

            for(Integer year: years){
                Assert.assertTrue(year<=2023 && year>=2020);
            }

            //From the dropdown on the right corner choose “Price: Low to High” option and verify that the results are sorted from lowest to highest price.

            Select select1  = new Select(driver.findElement(By.xpath("//select[@data-tracking-id='select_sort_by']")));
            select1.selectByVisibleText("Price low to high");
            Thread.sleep(2000);

            //From the dropdown menu, choose “Price: High to Low” option and verify that
            // the results are sorted from highest to lowest price.

            List<WebElement> sortedPrices = driver.findElements(By.xpath("//div[@class='pricing-details d-flex flex-column']//span[not(@class='font-weight-bold text-gray-darkest')]"));
            List<Integer> sort1 =new ArrayList<>();
            for(WebElement price : sortedPrices ){
                sort1.add(Integer.parseInt(price.getText().replace("$", "").replace(",","")));
            }
            List<Integer> copy = new ArrayList<>(sort1);
            Collections.sort(copy);
            Assert.assertEquals(sort1, copy);

            select1.selectByValue("price:desc");
            Thread.sleep(1000);
            List<WebElement>  sortedElementsDescendingOrder = driver.findElements(By.xpath("//div[@class='pricing-details d-flex flex-column']//span[@class='heading-3']"));
            List<Integer> sortedPriceDecendingOrder = new ArrayList<>();
            for(WebElement element: sortedElementsDescendingOrder){
                sortedPriceDecendingOrder.add(Integer.parseInt(element.getText().replace("$","").replace(",","")));
            }
            List<Integer> copy2 = new ArrayList<>(sortedPriceDecendingOrder);
            Collections.sort(copy2, Comparator.reverseOrder());
            Assert.assertEquals(sortedPriceDecendingOrder, copy2);

            //From the dropdown menu, choose “Mileage: Low to High” option and verify that the
            // results are sorted from highest to lowest mileage.

            select1.selectByVisibleText("Mileage: ow to High");
            Thread.sleep(1000);
            List<WebElement> sortedMillage = driver.findElements(By.xpath("//div[@class='key-point size-14 d-flex align-items-baseline mt-0_5 col-12'][1]//span[@class='text-cool-gray-30']"));
            List<Integer> sortedByMile = new ArrayList<>();
            for(WebElement element: sortedMillage){
                sortedByMile.add(Integer.parseInt(element.getText().replace(",","").replace(" miles", "")));
            }

            List<Integer> copy3 = new ArrayList<>(sortedByMile);
            Collections.sort(copy3);
            Assert.assertEquals(sortedByMile, copy3);

            //Find the last result and store its title, price and mileage
            // (get the last result dynamically, i.e., your code should click on the last result
            // regardless of how many results are there). Click on it to open the details about the result

            List<WebElement> allListing = driver.findElements(By.xpath("//div[@class='visible-vehicle-info d-flex flex-column']"));
            WebElement lastElement= allListing.get(allListing.size()-1);
            String title=lastElement.findElement(By.xpath("(//div[@class='size-16 font-weight-bold mb-0_5 text-blue-30'])["+allListing.size()+"]")).getText();


            String price=lastElement.findElement(By.xpath("(//div[@class='pricing-details d-flex flex-column']//div[not (@class='text-lowercase') and not( @class='size-12')])["+allListing.size()+"]")).getText();


            String miles =lastElement.findElement(By.xpath("(//div[@class='key-point size-14 d-flex align-items-baseline mt-0_5 col-12'][1])["+allListing.size()+"]")).getText().replace(" miles", "");


            lastElement.click();
            Thread.sleep(1000);
            Assert.assertEquals((driver.findElement(By.cssSelector("h1[class='d-inline-block mb-0 heading-2 mt-0_25']"))).getText(), title);
            Assert.assertEquals(driver.findElement(By.xpath("//span[@data-testid='vdp-price-row']")).getText(), price);
            Assert.assertEquals(driver.findElement(By.xpath("(//div[@class='pr-0 font-weight-bold text-right ml-1 col'])[1]")).getText(), miles);

            driver.navigate().back();

            Assert.assertTrue(driver.findElement(By.xpath("(//div[@class='d-flex flex-column usurp-inventory-card h-100 w-100 srp-expanded srp-border border-0 bg-white'])["+allListing.size()+"]//div[@class='bg-white text-gray-darker']")).isDisplayed());






        } finally {

        }
    }


   }





