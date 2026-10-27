package com.liverpool.qa.screenplay.tasks.landing;

import com.liverpool.qa.screenplay.utils.EnvironmentConfig;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirLanding implements Task {


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(EnvironmentConfig.baseUrl()));
    }


    public static AbrirLanding principal() {
        return new AbrirLanding();
    }
}

