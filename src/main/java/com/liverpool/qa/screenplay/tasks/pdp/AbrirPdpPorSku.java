package com.liverpool.qa.screenplay.tasks.pdp;

import com.liverpool.qa.screenplay.utils.EnvironmentConfig;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;

public class AbrirPdpPorSku implements Task {

    private final String sku;

    private AbrirPdpPorSku(String sku) {
        this.sku = sku;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.url(EnvironmentConfig.baseUrl() + "/tienda/home"));
    }

    public static AbrirPdpPorSku conSku(String sku) {
        return new AbrirPdpPorSku(sku);
    }

}

