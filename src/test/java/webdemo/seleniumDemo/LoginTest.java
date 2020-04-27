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

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest {
    private static WebDriver driver;
    private WebElement inputEmail;
    private WebElement inputPassword;
    private WebElement submitButton;
    private String testLogin = "aga.test.selenium@gmail.com";
    private String testPasswd = "selenium_passwd";

    @BeforeAll
    public static void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver" + (System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : "" ));
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
     public void setUp() {
        driver.get("https://github.com/login");
    }

    private void logIn(String user, String passwd){
        this.inputEmail = driver.findElement(By.id("login_field"));
        this.inputPassword = driver.findElement(By.id("password"));
        this.submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        this.inputEmail.sendKeys(user);
        this.inputPassword.sendKeys(passwd);
        this.submitButton.click();
    }

    @Test
    public void testEmptyLoginAndPassword() {
        logIn("","");
        WebElement message = driver.findElement(By.xpath("//div[@class='container-lg px-2']"));
        assertEquals("Incorrect username or password.", message.getText());
    }
    @Test
    public void testEmptyPassword() {
        logIn("user","");
        WebElement message = driver.findElement(By.xpath("//div[@class='container-lg px-2']"));
        assertEquals("Incorrect username or password.", message.getText());
    }
    @Test
    public void testEmptyLogin() {
        logIn("","pass");
        WebElement message = driver.findElement(By.xpath("//div[@class='container-lg px-2']"));
        assertEquals("Incorrect username or password.", message.getText());
    }

    @Test
    public void testIncorrectLogin() {
        logIn("not_existing@gmail.com","pass");
        WebElement message = driver.findElement(By.xpath("//div[@class='container-lg px-2']"));
        assertEquals("Incorrect username or password.", message.getText());
    }

    @Test
    public void testIncorrectPassword() {
        logIn(testLogin,"pass");
        WebElement message = driver.findElement(By.xpath("//div[@class='container-lg px-2']"));
        assertEquals("Incorrect username or password.", message.getText());
    }

    @Test
    public void testCorrectLoginAndPassword() {
        logIn(testLogin,testPasswd);
        String title = driver.getTitle();
        assertTrue(title.contains("DuckDuckGo"));
    }

    @Test
    public void testForgetPasswordPage(){
        WebElement forget = driver.findElement(By.xpath("//a[@href='/password_reset']"));
        forget.click();
        assertEquals("Forgot your password? · GitHub", driver.getTitle());
    }


    @AfterAll
    public static void tearDown() throws Exception {
        driver.quit();
        driver.close();
    }

}
