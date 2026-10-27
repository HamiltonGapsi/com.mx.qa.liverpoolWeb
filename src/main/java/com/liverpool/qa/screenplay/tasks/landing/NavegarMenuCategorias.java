package com.liverpool.qa.screenplay.tasks.landing;

import com.liverpool.qa.screenplay.ui.LandingPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Despliega el menú "Categorías" y navega Mujer > Bolsas > Tote.
 * El nivel "Bolsas" es el agrupador visible al posicionarse sobre "Mujer",
 * por lo que se llega a "Tote" desde el submenú de "Mujer".
 */
public class NavegarMenuCategorias implements Task {

    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LandingPage.BOTON_CATEGORIAS_HEADER, isClickable()).forNoMoreThan(TIMEOUT),
                Click.on(LandingPage.BOTON_CATEGORIAS_HEADER),
                WaitUntil.the(LandingPage.OPCION_CATEGORIA_MUJER, isVisible()).forNoMoreThan(TIMEOUT),
                MoveMouse.to(LandingPage.OPCION_CATEGORIA_MUJER),
                WaitUntil.the(LandingPage.OPCION_SUBCATEGORIA_TOTE, isVisible()).forNoMoreThan(TIMEOUT),
                MoveMouse.to(LandingPage.OPCION_SUBCATEGORIA_TOTE),
                Click.on(LandingPage.OPCION_SUBCATEGORIA_TOTE)
        );
    }

    public static NavegarMenuCategorias haciaMujerBolsasTote() {
        return new NavegarMenuCategorias();
    }
}
