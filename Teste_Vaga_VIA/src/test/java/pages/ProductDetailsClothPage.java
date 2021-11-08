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
import java.util.Random;

public class ProductDetailsClothPage {
    private WebDriver d;
    private static Wait<WebDriver> wait;
    public final String url = "http://lojaebac.ebaconline.art.br/product/";

    private final By cores = By.xpath("//ul[@aria-label=\"Color\"]/li");
    private final By btnComprar = By.xpath("//button[.='Comprar']");
    private final By btnVerCarrinho = By.linkText("Ver carrinho");
    private final By txtProductName = By.xpath("//h1[@class='product_title entry-title']");

    public ProductDetailsClothPage(WebDriver d) {
        this.d = d;
        wait = new FluentWait<WebDriver>(d)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    private By getSizeXpath(String s) {
        switch (s.toUpperCase()) {
            case "XS": {
                return By.xpath("//li[@data-wvstooltip='XS']");
            }
            case "S": {
                return By.xpath("//li[@data-wvstooltip='S']");
            }
            case "L": {
                return By.xpath("//li[@data-wvstooltip='L']");
            }
            case "XL": {
                return By.xpath("//li[@data-wvstooltip='XL']");
            }
            case "M":
            default: {
                return By.xpath("//li[@data-wvstooltip='M']");
            }
        }
    }

    public void clicarEmTamanho(String tam) {
        wait.until(ExpectedConditions.elementToBeClickable(getSizeXpath(tam))).click();
    }

    public void selecionarCorRandomica() {
        List<WebElement> itens = d.findElements(cores);
        Random r = new Random();
        wait.until(ExpectedConditions.elementToBeClickable(itens.get(r.nextInt(itens.size())))).click();
    }

    public String clicarEmComprar() {
        String name = wait.until(ExpectedConditions.elementToBeClickable(txtProductName)).getText();
        wait.until(ExpectedConditions.elementToBeClickable(btnComprar)).click();
        return name;
    }

    public void clicarEmVerCarrinho() {
        wait.until(ExpectedConditions.elementToBeClickable(btnVerCarrinho)).click();
    }


}
