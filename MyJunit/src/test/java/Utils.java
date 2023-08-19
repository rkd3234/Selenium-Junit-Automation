import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.idealized.Javascript;

public class Utils {
    public static void doScroll(WebDriver driver, String height){

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+height+")");
}
}
