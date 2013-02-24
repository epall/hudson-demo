import com.thoughtworks.selenium.*;
import java.util.Properties;
import java.io.FileInputStream;
 
public class SmokeTest extends SeleneseTestCase {
    public void setUp() throws Exception {
        String username = System.getenv("SAUCE_USER_NAME");
        String apiKey = System.getenv("SAUCE_API_KEY");
        if(username == null || apiKey == null){
            System.err.println("Sauce OnDemand credentials not set.");
            System.err.println("Please put your Sauce OnDemand credentials into the environment variables SAUCE_ONDEMAND_USERNAME and SAUCE_ONDEMAND_ACCESS_KEY");
        }
        DefaultSelenium selenium = new DefaultSelenium(
                "saucelabs.com",
                4444,
                "{\"username\": \"" + username + "\"," +
                "\"access-key\": \"" + apiKey + "\"," +
                "\"os\": \"Windows 2003\"," +
                "\"browser\": \"firefox\"," +
                "\"browser-version\": \"3.\"," +
                "\"job-name\": \"Demo of Hudson + Sauce OnDemand\"}",
                "http://myapp.test/");
        selenium.start();
        this.selenium = selenium;
    }
    public void tearDown() throws Exception {
        if (selenium != null) {
            selenium.stop();
            selenium = null;
        }
    }
    
    public void testSauce() throws Exception {
        this.selenium.open("/");
        assertTrue(this.selenium.isTextPresent("Hello, World!"));
    }
}
