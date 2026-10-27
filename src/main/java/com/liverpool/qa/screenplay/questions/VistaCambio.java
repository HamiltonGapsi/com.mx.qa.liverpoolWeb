package com.liverpool.qa.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Indica si el navegador dejó la URL indicada (se cargó otra vista) dentro del tiempo máximo.
 */
public class VistaCambio implements Question<Boolean> {

    private final String urlAnterior;
    private final Duration timeout;

    private VistaCambio(String urlAnterior, Duration timeout) {
        this.urlAnterior = urlAnterior;
        this.timeout = timeout;
    }

    public static VistaCambio desde(String urlAnterior, Duration timeout) {
        return new VistaCambio(urlAnterior, timeout);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), timeout)
                    .until(driver -> !urlAnterior.equals(driver.getCurrentUrl()));
            return true;
        } catch (org.openqa.selenium.TimeoutException e) {
            return false;
        }
    }
}
