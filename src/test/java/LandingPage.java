import org.openqa.selenium.By;

/**
 * Created by RXC8414 on 5/17/2017.
 */
public class LandingPage extends MainPage {
    public static final String HEADER_SYMBOL = ".//span[@id='headerSymbol' and text()='HD']";
    public static final String STOCK_PRICE = ".//span[@id='lastPrice']";

    public boolean getPriceValue(){
        if(waitUntilElementDisplayed(HEADER_SYMBOL)){
            try {
                String stockPrice = getStockValue();
                System.out.println(stockPrice);
                return true;
            }catch(Exception e){
                return false;
            }
        }
        return false;
    }

    private String getStockValue(){
        return driver.findElement(By.xpath(STOCK_PRICE)).getText();
    }
}
