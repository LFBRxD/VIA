import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import pages.HomePage;
import pages.ProductCheckouPage;
import pages.ProductDetailsClothPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Desafio {
    private static String defaultUrl = "http://lojaebac.ebaconline.art.br";
    private static WebDriver driver;
    private static Wait<WebDriver> wait;

    @BeforeAll
    public static void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        chromeOptions.addArguments("start-maximized");

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chrome/v95/chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);

        wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    @Test
    public void test1() {
        List<String> products = new ArrayList<String>();

        driver.get(defaultUrl);
        HomePage hp = new HomePage(driver);
        hp.clicarEmElementoCompravelByIndex(0);

        ProductDetailsClothPage pd = new ProductDetailsClothPage(driver);
        pd.clicarEmTamanho("S");
        pd.selecionarCorRandomica();
        products.add(pd.clicarEmComprar());

        delayInSegundos(1);
        hp.clicarEmBtnHome();

        hp.clicarEmElementoCompravelByIndex(2);
        pd.clicarEmTamanho("M");
        pd.selecionarCorRandomica();
        products.add(pd.clicarEmComprar());

        pd.clicarEmVerCarrinho();

        ProductCheckouPage pc = new ProductCheckouPage(driver);
        assertEquals(2, pc.listaAdicionada(products));

    }

    @AfterAll
    public static void tearDown() {
        delayInSegundos(10);
        driver.quit();
    }


    private static void delayInSegundos(int valor) {
        try {
            Thread.sleep(valor * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
