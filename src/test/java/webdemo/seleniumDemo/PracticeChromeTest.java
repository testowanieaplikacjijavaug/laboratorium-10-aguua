package webdemo.seleniumDemo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class PracticeChromeTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver" + (System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : "" ));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        chromeOptions.addArguments("--lang=en-US");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() {
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void testTitlePage() {
        assertEquals("My Store", driver.getTitle());
    }

    @Test
    public void findById() {
        WebElement element = driver.findElement(By.id("search_block_top"));
        assertNotNull(element);
    }
    @Test
    public void testClick() {
        driver.findElement(By.id("contact-link")).click();
        assertTrue(driver.getTitle().contains("Contact us"));

    }

    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
    }


}
