import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by RXC8414 on 5/17/2017.
 */
public class Application {
    static LandingPage selenium = new LandingPage();

    @Test
    public void verifyStockPrice(){
        // Assert that we land at Fidelity.com
        Assert.assertTrue("Could not open www.fidelity.com",
                selenium.navigateURL("https://www.fidelity.com"));
        System.out.println("Verified landed successfully at Fidelity.com");

        // Assert that search stock text field is displayed
        Assert.assertTrue("Could not find the stock search box.", selenium.verifySearchBox());
        System.out.println("Verified stock search box is present and enable.");

        // Search for stock = hd
        Assert.assertTrue("Could not search for the stock symbol HD.",selenium.verifySearchStock());
        System.out.println("Verified no errors from search.");

        // Verify Landing Page and remove price value
        Assert.assertTrue("Could not verify landing page has header symbol of HD", selenium.getPriceValue());
        System.out.println("Verified stock symbol and price.");
    }

    @AfterClass
    public static void cleanUp(){
        selenium.closeDriver();
    }
}
