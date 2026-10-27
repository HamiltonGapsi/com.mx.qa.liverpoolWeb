package com.liverpool.qa.screenplay.tasks.pdp;

import com.liverpool.qa.screenplay.ui.PdpPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ValidarBolsa implements Task {

    @Override
    @Step("{0} verifica que la bolsa contiene al menos un producto visible")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(PdpPage.PRIMER_PRODUCTO_EN_BOLSA, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(30)),
                Ensure.that(PdpPage.PRIMER_PRODUCTO_EN_BOLSA).isDisplayed()
        );
    }

    public static ValidarBolsa conProductos() {
        return new ValidarBolsa();
    }
}
