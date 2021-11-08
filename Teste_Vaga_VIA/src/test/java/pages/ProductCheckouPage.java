package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProductCheckouPage {
    private WebDriver d;
    private static Wait<WebDriver> wait;
    public final String url = "http://lojaebac.ebaconline.art.br/carrinho/";

    public ProductCheckouPage(WebDriver d) {
        this.d = d;
        wait = new FluentWait<WebDriver>(d)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }

    public int listaAdicionada(List<String> s) {
        List<String> products = new ArrayList<String>();
        String xpath_list = "//tr[@class='cart_item']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath_list)));

        for (String el : s) {
            String xpath = "//tr/td/a[contains(text(),'" + el + "')]";
            if (!d.findElement(By.xpath(xpath)).isEnabled()) {
                return 0;
            }
        }
        return d.findElements(By.xpath(xpath_list)).size();
    }


}
