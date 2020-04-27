package webdemo.seleniumDemo;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.*;


public class StepikTest {
    private static WebDriver driver;
    String url = "https://stepik.org/";

    @BeforeAll
    public static void setUpDriver(){
        System.setProperty("webdriver.gecko.driver", "resources/geckodriver" + (System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : "" ));
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        options.addPreference("intl.accept_languages", "en-us");
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver.get(url);
    }


    //Sprawdzi ilość dostępnych linków na stronie
    @Test
    public void countAvailableLinks(){
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        System.out.println(links.size()+" links on " + url);
        assertFalse(links.isEmpty());
    }

    //Sprawdzi ilość obrazów dostępnych na stronie
    @Test
    public void countImages(){
        List<WebElement> images = driver.findElements(By.xpath("//img"));
        System.out.println(images.size()+" images on" + url);
        assertFalse(images.isEmpty());
    }

    //Przejdzie do każdego linka i wróci spowrotem do sprawdzanej strony internetowej
    @Test
    @Disabled
    public void goToLinksAndReturn(){
        String mainTitle = driver.getTitle();
        List<String> hrefs =
                driver
                        .findElements(By.xpath("//a[@href and string-length(@href)!=0 ]"))
                        .stream()
                        .map(x -> x.getAttribute("href"))
                        .filter(x -> !x.startsWith("mailto"))
                        .filter(x-> !x.endsWith("xml"))
                        .collect(Collectors.toList());

        for (String href : hrefs) {
            driver.get(href);
            driver.navigate().back();
        }
        assertEquals(mainTitle, driver.getTitle());
    }




    //Przejdzie do dowolnego formularza i sprawdzi ilość jego pól testowych
    @Test
    public void countFormFields(){
        driver.get("https://stepik.org/new-course");
        List<WebElement> forms = driver.findElement(By.xpath("//form")).findElements(By.xpath("./*"));
        System.out.println(forms.size()+" forms fields on " + url);
        assertFalse(forms.isEmpty());
    }

    @AfterAll
    public static void tearDown() {
        driver.close();
    }
}
