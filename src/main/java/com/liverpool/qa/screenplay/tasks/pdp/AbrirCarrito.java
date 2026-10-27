package com.liverpool.qa.screenplay.tasks.pdp;

import com.liverpool.qa.screenplay.ui.PdpPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AbrirCarrito implements Task {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    @Override
    @Step("{0} abre su bolsa de compras")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(PdpPage.ICONO_CARRITO, isVisible()).forNoMoreThan(TIMEOUT),
                JavaScriptClick.on(PdpPage.ICONO_CARRITO),
                WaitUntil.the(PdpPage.CONTENEDOR_BOLSA, isVisible()).forNoMoreThan(TIMEOUT)
        );
    }

    public static AbrirCarrito desdeElHeader() {
        return new AbrirCarrito();
    }
}
