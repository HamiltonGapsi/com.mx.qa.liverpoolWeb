package com.liverpool.qa.screenplay.tasks.pdp;

import com.liverpool.qa.screenplay.interactions.CentrarElemento;
import com.liverpool.qa.screenplay.ui.PdpPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;

public class AgregarAlCarrito implements Task {

    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    @Override
    @Step("{0} agrega el producto a su bolsa")
    public <T extends Actor> void performAs(T actor) {
        String contadorAntes = contadorBolsa(actor);

        actor.attemptsTo(
                CentrarElemento.en(PdpPage.BOTON_AGREGAR_CARRITO),
                WaitUntil.the(PdpPage.BOTON_AGREGAR_CARRITO, isClickable()).forNoMoreThan(TIMEOUT),
                Click.on(PdpPage.BOTON_AGREGAR_CARRITO)
        );

        // El alta en la bolsa es asíncrona: se espera a que el contador del header se actualice.
        new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), TIMEOUT)
                .withMessage("El contador de la bolsa no se actualizó después de agregar el producto")
                .until(driver -> !contadorBolsa(actor).equals(contadorAntes));
    }

    private static String contadorBolsa(Actor actor) {
        return PdpPage.ICONO_CARRITO.resolveFor(actor).getText().trim();
    }

    public static AgregarAlCarrito elProducto() {
        return new AgregarAlCarrito();
    }
}
