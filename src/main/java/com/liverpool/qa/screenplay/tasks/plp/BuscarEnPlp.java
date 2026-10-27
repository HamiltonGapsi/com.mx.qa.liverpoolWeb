package com.liverpool.qa.screenplay.tasks.plp;

import com.liverpool.qa.screenplay.utils.EnvironmentConfig;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BuscarEnPlp implements Task {

    private final String termino;

    private BuscarEnPlp(String termino) {
        this.termino = termino;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        String encoded = URLEncoder.encode(termino, StandardCharsets.UTF_8);
        actor.attemptsTo(Open.url(EnvironmentConfig.baseUrl() + "/tienda?s=" + encoded));
    }

    public static BuscarEnPlp conTermino(String termino) {
        return new BuscarEnPlp(termino);
    }

}

