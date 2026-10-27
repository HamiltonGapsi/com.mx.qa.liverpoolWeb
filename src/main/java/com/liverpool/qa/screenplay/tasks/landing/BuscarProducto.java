package com.liverpool.qa.screenplay.tasks.landing;

import com.liverpool.qa.screenplay.ui.LandingPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class BuscarProducto implements Task {

    private final String termino;

    private BuscarProducto(String termino) {
        this.termino = termino;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LandingPage.BARRA_BUSQUEDA, isVisible()).forNoMoreThan(Duration.ofSeconds(20)),
                Click.on(LandingPage.BARRA_BUSQUEDA),
                Clear.field(LandingPage.BARRA_BUSQUEDA),
                Enter.theValue(termino).into(LandingPage.BARRA_BUSQUEDA).thenHit(Keys.ENTER)
        );
    }

    public static BuscarProducto conTermino(String termino) {
        return new BuscarProducto(termino);
    }
}
