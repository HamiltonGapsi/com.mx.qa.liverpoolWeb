package com.liverpool.qa.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Indica si el texto de un elemento contiene el valor esperado, sin distinguir mayúsculas.
 */
public class TextoContiene implements Question<Boolean> {

    private final Target target;
    private final String esperado;

    private TextoContiene(Target target, String esperado) {
        this.target = target;
        this.esperado = esperado;
    }

    public static TextoContiene en(Target target, String esperado) {
        return new TextoContiene(target, esperado);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String texto = actor.asksFor(Text.of(target));
        return texto != null && texto.trim().toLowerCase().contains(esperado.trim().toLowerCase());
    }
}
