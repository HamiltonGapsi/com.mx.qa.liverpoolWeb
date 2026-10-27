package com.liverpool.qa.screenplay.tasks.landing;

import com.liverpool.qa.screenplay.ui.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AccederALogin implements Task {

    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(LoginPage.INICIO_SESION, isClickable()).forNoMoreThan(TIMEOUT),
                Click.on(LoginPage.INICIO_SESION),
                // Sin sesión activa el banner redirige directo al login; el menú desplegable solo aparece en algunos estados.
                Check.whether(Visibility.of(LoginPage.OPCION_INICIAR_SESION))
                        .andIfSo(Click.on(LoginPage.OPCION_INICIAR_SESION)),
                WaitUntil.the(LoginPage.CAMPO_USUARIO, isVisible()).forNoMoreThan(TIMEOUT)
        );
    }

    public static AccederALogin desdeElBanner() {
        return new AccederALogin();
    }
}
