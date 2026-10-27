package com.liverpool.qa.screenplay.tasks.plp;

import com.liverpool.qa.screenplay.questions.CurrentUrlContainsIgnoringCase;
import com.liverpool.qa.screenplay.ui.PlpPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class ValidarResultados implements Task {

    private final String termino;

    private ValidarResultados(String termino) {
        this.termino = termino;
    }

    @Override
    @Step("{0} verifica los resultados de la busqueda de #termino")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(PlpPage.GRILLA_RESULTADOS, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(30)),
                WaitUntil.the(PlpPage.NOMBRE_PRIMER_PRODUCTO, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(30)),
                Ensure.thatTheAnswerTo("la URL conserva el termino buscado",
                        CurrentUrlContainsIgnoringCase.text("s=" + termino)).isTrue(),
                Ensure.that(PlpPage.GRILLA_RESULTADOS).isDisplayed(),
                Ensure.that(Text.of(PlpPage.NOMBRE_PRIMER_PRODUCTO).answeredBy(actor).trim()).isNotEmpty()
        );
    }

    public static ValidarResultados para(String termino) {
        return new ValidarResultados(termino);
    }
}
