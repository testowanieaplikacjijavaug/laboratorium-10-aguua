package webdemo.seleniumDemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PracticeOperaTest {
    private static WebDriver driver;

    @BeforeAll
    public static void setUpDriver() {
        System.setProperty("webdriver.opera.driver", "resources/operadriver"+ (System.getProperty("os.name").toLowerCase().contains("win") ? ".exe" : "" ));
        OperaOptions operaOptions = new OperaOptions();
        operaOptions.addArguments("--headless");
        driver = new OperaDriver(operaOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @BeforeEach
    public void setUp() {
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void EasyTest() {
        assertTrue(true);
    }

}
