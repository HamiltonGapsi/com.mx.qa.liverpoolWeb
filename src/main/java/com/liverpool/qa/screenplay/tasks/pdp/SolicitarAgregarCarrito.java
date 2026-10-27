package com.liverpool.qa.screenplay.tasks.pdp;

import com.liverpool.qa.screenplay.ui.PdpPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isPresent;

public class SolicitarAgregarCarrito implements Task {

    private final String sku;

    private SolicitarAgregarCarrito(String sku) {
        this.sku = sku;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.remember("skuAddToCartRequested", sku);

        actor.attemptsTo(
                // 1. Seleccionar primer producto del carrusel
                WaitUntil.the(PdpPage.PRODUCTO_TENDENCIA_1, isClickable()).forNoMoreThan(15).seconds(),
                Click.on(PdpPage.PRODUCTO_TENDENCIA_1),

                // 2. Esperar a que el botón exista en el DOM, hacer Scroll hacia él y hacer clic con JS
                WaitUntil.the(PdpPage.BOTON_AGREGAR_CARRITO, isPresent()).forNoMoreThan(15).seconds(),
                Scroll.to(PdpPage.BOTON_AGREGAR_CARRITO),
                JavaScriptClick.on(PdpPage.BOTON_AGREGAR_CARRITO),

                // 3. Clic en el icono del carrito arriba
                WaitUntil.the(PdpPage.ICONO_CARRITO, isClickable()).forNoMoreThan(15).seconds(),
                JavaScriptClick.on(PdpPage.ICONO_CARRITO),

                // 4. Clic en Comprar ahora
                WaitUntil.the(PdpPage.BOTON_COMPRAR_AHORA, isClickable()).forNoMoreThan(15).seconds(),
                Click.on(PdpPage.BOTON_COMPRAR_AHORA)
        );
    }

    public static SolicitarAgregarCarrito deSku(String sku) {
        return new SolicitarAgregarCarrito(sku);
    }
}