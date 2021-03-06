import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by RXC8414 on 5/9/2017.
 */
public class SeleniumUtils {
    public static final int MAX_TIME = 15;
    public WebDriver driver;
    public static final String DRIVER_PATH = "C:\\GitHub Repos\\DotCom\\chromedriver.exe";

    public SeleniumUtils(){
        //Setting Chrome driver properties
        System.setProperty("webdriver.chrome.driver",DRIVER_PATH);
        // Completing instantiation
        driver = new ChromeDriver();
        //driver.manage().window().maximize();
    }

    public boolean waitUntilElementDisplayed(String expression){
        int counter = 0;
        do {
            if (verifyDisplayed(expression)){
                return true;
            }
            else if (counter > 3){
                if(verifyEnabled(expression)){
                    return true;
                }

                else if (verifyLocation(expression)){
                    return true;
                }
            }

            counter++;
            try {
                TimeUnit.MILLISECONDS.sleep(950);
            }catch(Exception e){
                return false;
            }
        } while (counter < 20);

        return false;
    }

    public boolean verifyDisplayed(String expression){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(By.xpath(expression)).isDisplayed()) {
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }

    public boolean verifyEnabled(String expression){
        try {
            TimeUnit.MILLISECONDS.sleep(50);
            if (driver.findElement(By.xpath(expression)).isEnabled()) {
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }

    public boolean verifyLocation(String expression){
        try{
            TimeUnit.MILLISECONDS.sleep(50);
            if(driver.findElement(By.xpath(expression)).getLocation().x < 0 ||
                    driver.findElement(By.xpath(expression)).getLocation().y < 0){
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }


    // Selenium command for navigating to a page
    public boolean navigateURL(String url){
        try{
            driver.get(url); // Selenium command
            return true;
        }catch(Exception e){
            return false;
        }
    }

    // Verify that you land on the correct page base on an element verification
    public boolean verifyLandingPage(String xpath){
        // Make sure element is displayed on landing page
        // Validation happening through XPath
        if(driver.findElement(By.xpath(xpath)).isDisplayed()){
            return true;
        }
        return false;
    }

    // Validate presence of text box
    public boolean validateTextBox(String element){
        if(waitUntilElementDisplayed(element)){
            return true;
        }
        return false;
    }

    // Entering text into a text box
    public boolean enterTextIntoTextBox(String element, String text){
        if(waitUntilElementDisplayed(element)){
            try {
                syncElement("MILLISECONDS",100); // Syncing Selenium
                driver.findElement(By.xpath(element)).sendKeys(text); //command for entering text
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }

    public boolean validateButton(String element){
        if(waitUntilElementDisplayed(element)){
            try {
                syncElement("MILLISECONDS",100); //Syncing
                driver.findElement(By.xpath(element)).click(); // command for click
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }

    public void syncElement(String time, int amount){
        try {
            switch (time.toUpperCase()) {
                case "MILLI":
                case "MILLISECONDS":
                    TimeUnit.MILLISECONDS.sleep(amount);
                    break;
                case "SEC":
                case "SECONDS":
                    TimeUnit.SECONDS.sleep(amount);
                    break;
            }
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }
    }

    public String insertIndexIntoXpath(String xpath, int index){
        return xpath.replace("[]","["+ index +"]");
    }

    public boolean switchDriver(String target, String iFrame){
        if(target.toUpperCase().equals("IFRAME")){
            try {
                driver.switchTo().frame(driver.findElement(By.xpath(iFrame)));
                return true;
            }catch(Exception e){
                return false;
            }
        }
        else{
            try {
                driver.switchTo().defaultContent();
                return true;
            }catch(Exception e){
                return false;
            }
        }
    }

    public void closeDriver(){
        driver.close();
    }
}
