package com.liverpool.qa.screenplay.actors;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

public final class UsuarioWeb {

    private UsuarioWeb() {
    }

    public static Actor llamado(String nombre, WebDriver driver) {
        return Actor.named(nombre).whoCan(BrowseTheWeb.with(driver));
    }
}

