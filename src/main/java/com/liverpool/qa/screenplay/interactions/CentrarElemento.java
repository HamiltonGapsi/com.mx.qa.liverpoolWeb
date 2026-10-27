package com.liverpool.qa.screenplay.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Centra el elemento en pantalla para que el header fijo o el banner de cookies no intercepten el clic.
 */
public class CentrarElemento implements Interaction {

    private final Target target;

    public CentrarElemento(Target target) {
        this.target = target;
    }

    @Override
    @Step("{0} centra en pantalla #target")
    public <T extends Actor> void performAs(T actor) {
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", target.resolveFor(actor));
    }

    public static CentrarElemento en(Target target) {
        return new CentrarElemento(target);
    }
}
