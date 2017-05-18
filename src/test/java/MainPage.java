import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import javax.swing.text.Utilities;

/**
 * Created by RXC8414 on 5/17/2017.
 */
public class MainPage extends SeleniumUtils{
    public static final String searchBox = ".//input[@id='nav-search-mobile']";

    public boolean verifySearchBox(){
        if(waitUntilElementDisplayed(searchBox)){
            return true;
        }
        return false;
    }

    public boolean verifySearchStock(){
        try {
            // clear text box
            driver.findElement(By.xpath(searchBox)).clear();
            // entering stock = hd
            driver.findElement(By.xpath(searchBox)).sendKeys("hd");
            // hitting Enter on the keyboard
            driver.findElement(By.xpath(searchBox)).sendKeys(Keys.RETURN);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
