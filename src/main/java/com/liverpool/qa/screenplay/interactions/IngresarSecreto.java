package com.liverpool.qa.screenplay.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Escribe un valor sensible sin exponerlo en el reporte de Serenity.
 */
public class IngresarSecreto implements Interaction {

    private final String valor;
    private final Target campo;

    public IngresarSecreto(String valor, Target campo) {
        this.valor = valor;
        this.campo = campo;
    }

    @Override
    @Step("{0} ingresa un valor protegido en #campo")
    public <T extends Actor> void performAs(T actor) {
        campo.resolveFor(actor).clear();
        campo.resolveFor(actor).sendKeys(valor);
    }

    public static IngresarSecreto en(Target campo, String valor) {
        return new IngresarSecreto(valor, campo);
    }
}
