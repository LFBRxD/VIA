package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver d;
    private static Wait<WebDriver> wait;
    public final String url = "http://lojaebac.ebaconline.art.br/";
    private final By elementoCompravel = By.xpath("//*[contains(@class,'purchasable')]");
    private final By btnHome = By.xpath("(//div[@class='logo']/a/img)[2]");

    public HomePage(WebDriver d) {
        this.d = d;
        wait = new FluentWait<WebDriver>(d)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    public void clicarEmElementoCompravel() {
        wait.until(ExpectedConditions.elementToBeClickable(elementoCompravel)).click();
    }

    public void clicarEmElementoCompravelByIndex(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(elementoCompravel));
        List<WebElement> itens = d.findElements(elementoCompravel);
        if (itens.size() < index) {
            wait.until(ExpectedConditions.elementToBeClickable(itens.get(0))).click();
        } else {
            wait.until(ExpectedConditions.elementToBeClickable(itens.get(index))).click();
        }
    }

    public void clicarEmBtnHome() {
        wait.until(ExpectedConditions.elementToBeClickable(btnHome)).click();
    }

}
