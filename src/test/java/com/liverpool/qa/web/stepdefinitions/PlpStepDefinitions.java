package com.liverpool.qa.web.stepdefinitions;

import com.liverpool.qa.screenplay.actors.UsuarioWeb;
import com.liverpool.qa.screenplay.questions.CurrentUrlContains;
import com.liverpool.qa.screenplay.tasks.plp.BuscarEnPlp;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class PlpStepDefinitions {

    @Managed
    private WebDriver driver;

    private Actor shopper;

    @Before
    public void prepararActor() {
        shopper = UsuarioWeb.llamado("Shopper Web", driver);
    }

    @Dado("que el cliente abre una PLP usando datos externos")
    public void queElClienteAbreUnaPlpUsandoDatosExternos() {

    }

    @Cuando("navega a la lista de productos")
    public void navegaALaListaDeProductos() {
        // El acceso a PLP ya ocurre durante la apertura por termino de busqueda.
    }

    @Entonces("la URL contiene el criterio de búsqueda")
    public void laUrlContieneElCriterioDeBusqueda() {
        String searchTerm = shopper.recall("plpSearchTerm");
        shopper.attemptsTo(Ensure.thatTheAnswerTo(CurrentUrlContains.text(searchTerm)).isTrue());
    }
}

