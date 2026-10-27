package com.liverpool.qa.screenplay.tasks.pdp;

import com.liverpool.qa.screenplay.interactions.CentrarElemento;
import com.liverpool.qa.screenplay.ui.PdpPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

/**
 * Elige la primera talla disponible; si el producto no maneja tallas no hace nada.
 */
public class SeleccionarTalla implements Task {

    public static final String TALLA_SELECCIONADA = "tallaSeleccionada";
    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    @Override
    @Step("{0} selecciona la primera talla disponible")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(WaitUntil.the(PdpPage.BOTON_AGREGAR_CARRITO, isVisible()).forNoMoreThan(TIMEOUT));

        if (!actor.asksFor(Visibility.of(PdpPage.PRIMERA_TALLA_DISPONIBLE))) {
            return;
        }

        actor.remember(TALLA_SELECCIONADA, actor.asksFor(Text.of(PdpPage.PRIMERA_TALLA_DISPONIBLE)).trim());
        actor.attemptsTo(
                CentrarElemento.en(PdpPage.PRIMERA_TALLA_DISPONIBLE),
                Click.on(PdpPage.PRIMERA_TALLA_DISPONIBLE)
        );
    }

    public static SeleccionarTalla primeraDisponible() {
        return new SeleccionarTalla();
    }
}
