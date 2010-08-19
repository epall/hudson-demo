import com.thoughtworks.selenium.*;
import java.util.Properties;
import java.io.FileInputStream;
 
public class SmokeTest extends SeleneseTestCase {
    public void setUp() throws Exception {
        Properties configuration = new Properties();
        configuration.load(new FileInputStream("ondemand.properties"));
        if("YOUR_USERNAME".equals(configuration.getProperty("username"))){
            System.err.println("Sauce OnDemand username not set.");
            System.err.println("Please put your Sauce OnDemand credentails in ondemand.properties");
        }
        DefaultSelenium selenium = new DefaultSelenium(
                "saucelabs.com",
                4444,
                "{\"username\": \"" + configuration.getProperty("username") + "\"," +
                "\"access-key\": \"" + configuration.getProperty("access-key") + "\"," +
                "\"os\": \"Windows 2003\"," +
                "\"browser\": \"firefox\"," +
                "\"browser-version\": \"3.\"," +
                "\"job-name\": \"Demo of Hudson + Sauce OnDemand\"}",
                "http://myapp.test/");
        selenium.start();
        this.selenium = selenium;
    }
    
    public void testSauce() throws Exception {
        this.selenium.open("/");
        assertTrue(this.selenium.isTextPresent("Hello, World"));
    }
}
