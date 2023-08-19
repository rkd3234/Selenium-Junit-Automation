import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.table.JTableHeader;
import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyJunitTesting {
    WebDriver driver;
    @BeforeAll
    public void setup (){

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DisplayName("Visit the website and get title")
    @Test
    public void getTitle(){
        driver.get("https://demoqa.com/");
        String title_actual = driver.getTitle();
        String title_expected = "DEMOQA";
        System.out.println(title_actual);
        Assertions.assertTrue(title_actual.contains(title_expected));
    }
    @DisplayName("User should submit the form properly")
    @Test
    public void submitForm(){
        driver.get("https://demoqa.com/text-box");
       List<WebElement> txtBoxElement = driver.findElements(By.className("form-control"));
        txtBoxElement.get(0).sendKeys("M A K");
        txtBoxElement.get(1).sendKeys("MAK@test.com");
        txtBoxElement.get(2).sendKeys("Gulshan");
        txtBoxElement.get(3).sendKeys("Dhaka");
        Utils.doScroll(driver, "500");
        driver.findElement(By.id("submit")).click();

       // driver.findElement(By.id("userName")).sendKeys("M A K");
      //  driver.findElement(By.id("userEmail")).sendKeys("MAK@test.com");


        //Assertions.assertTrue(title_actual.contains(title_expected));
    }

    @Test
    public void clickOnButtons() {
        driver.get("https://demoqa.com/buttons");
        List<WebElement> buttons = driver.findElements(By.className("btn-primary"));
        Actions actions = new Actions(driver);
        actions.doubleClick(buttons.get(0)).perform();
        actions.doubleClick(buttons.get(1)).perform();
        actions.click(buttons.get(2)).perform();

        String messageActual = driver.findElement(By.id("doubleClickMessage")).getText();
        String messageExpected = "double click";
        Assertions.assertTrue(messageActual.contains(messageExpected));


    }

    @Test
    public void selectDate() {
        driver.get("https://demoqa.com/date-picker");
        WebElement datePickerElement = driver.findElement(By.id("datePickerMonthYearInput"));
        datePickerElement.click();
        datePickerElement.sendKeys(Keys.CONTROL + "a", Keys.BACK_SPACE);
        datePickerElement.sendKeys("6/10/2022", Keys.ENTER);


    }

    @Test
    public void selectDropdown() {
        driver.get("https://demoqa.com/select-menu");
        WebElement dropDownElem = driver.findElement(By.id("oldSelectMenu"));
        Select select = new Select(dropDownElem);
        select.selectByValue("3");

    }

    @Test
    public void selectAjaxDropdown() throws InterruptedException {
        driver.get("https://demoqa.com/select-menu");
        Utils.doScroll(driver, "500");
       driver.findElements(By.className("css-1hwfws3")).get(2).click();
        Thread.sleep(1000);
       //driver.findElement(By.className("css-1hwfws3")).sendKeys(Keys.ARROW_DOWN);
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.ARROW_DOWN).perform();
        Thread.sleep(1000);
        actions.keyDown(Keys.ARROW_DOWN).perform();
        actions.keyDown(Keys.ENTER).perform();
        //driver.findElement(By.className("css-1hwfws3")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
    }




    @AfterAll
    public void closeDriver(){
        //driver.quit();
    }
}
