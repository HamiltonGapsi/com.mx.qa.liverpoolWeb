package com.liverpool.qa.screenplay.tasks.landing;

import com.liverpool.qa.screenplay.interactions.IngresarSecreto;
import com.liverpool.qa.screenplay.ui.LoginPage;
import com.liverpool.qa.screenplay.utils.Credenciales;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isClickable;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class IniciarSesion implements Task {

    public static final String URL_LOGIN = "urlLogin";
    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    private final String ambiente;

    private IniciarSesion(String ambiente) {
        this.ambiente = ambiente;
    }

    @Override
    @Step("{0} inicia sesión con las credenciales del ambiente #ambiente")
    public <T extends Actor> void performAs(T actor) {
        Credenciales credenciales = Credenciales.delAmbiente(ambiente);

        actor.attemptsTo(
                WaitUntil.the(LoginPage.CAMPO_USUARIO, isVisible()).forNoMoreThan(TIMEOUT),
                Enter.theValue(credenciales.email()).into(LoginPage.CAMPO_USUARIO),
                IngresarSecreto.en(LoginPage.CAMPO_PASSWORD, credenciales.password()),
                WaitUntil.the(LoginPage.BOTON_INGRESAR, isClickable()).forNoMoreThan(TIMEOUT)
        );

        actor.remember(URL_LOGIN, BrowseTheWeb.as(actor).getDriver().getCurrentUrl());
        actor.attemptsTo(Click.on(LoginPage.BOTON_INGRESAR));
    }

    public static IniciarSesion conCredencialesDe(String ambiente) {
        return new IniciarSesion(ambiente);
    }
}
