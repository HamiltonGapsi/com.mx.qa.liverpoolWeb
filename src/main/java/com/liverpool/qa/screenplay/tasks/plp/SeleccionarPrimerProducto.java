package com.liverpool.qa.screenplay.tasks.plp;

import com.liverpool.qa.screenplay.interactions.CentrarElemento;
import com.liverpool.qa.screenplay.ui.PlpPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SeleccionarPrimerProducto implements Task {

    public static final String PRODUCTO_SELECCIONADO = "productoSeleccionado";
    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    @Override
    @Step("{0} selecciona el primer producto de la lista de resultados")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(PlpPage.GRILLA_RESULTADOS, isVisible()).forNoMoreThan(TIMEOUT),
                WaitUntil.the(PlpPage.NOMBRE_PRIMER_PRODUCTO, isVisible()).forNoMoreThan(TIMEOUT)
        );

        String nombre = actor.asksFor(Text.of(PlpPage.NOMBRE_PRIMER_PRODUCTO)).trim();
        actor.remember(PRODUCTO_SELECCIONADO, nombre);

        actor.attemptsTo(
                CentrarElemento.en(PlpPage.NOMBRE_PRIMER_PRODUCTO),
                Click.on(PlpPage.NOMBRE_PRIMER_PRODUCTO)
        );
    }

    public static SeleccionarPrimerProducto deLaLista() {
        return new SeleccionarPrimerProducto();
    }
}
