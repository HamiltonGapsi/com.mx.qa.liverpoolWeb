package com.liverpool.qa.web.stepdefinitions;

import com.liverpool.qa.screenplay.actors.UsuarioWeb;
import com.liverpool.qa.screenplay.questions.CurrentUrlContains;
import com.liverpool.qa.screenplay.questions.TextoContiene;
import com.liverpool.qa.screenplay.tasks.landing.AbrirLanding;
import com.liverpool.qa.screenplay.tasks.landing.BuscarProducto;
import com.liverpool.qa.screenplay.tasks.plp.SeleccionarPrimerProducto;
import com.liverpool.qa.screenplay.tasks.plp.ValidarResultados;
import com.liverpool.qa.screenplay.ui.PdpPage;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class PlpStepDefinitions {

    @Managed
    private WebDriver driver;

    private Actor shopper;

    @Before
    public void prepararActor() {
        shopper = UsuarioWeb.llamado("Shopper Web", driver);
    }

    @Dado("que el cliente esta en la landing para explorar el catalogo")
    public void queElClienteEstaEnLaLandingParaExplorarElCatalogo() {
        shopper.attemptsTo(AbrirLanding.principal());
    }

    @Cuando("busca {string} para consultar el catalogo")
    public void buscaParaConsultarElCatalogo(String termino) {
        shopper.remember("plpSearchTerm", termino);
        shopper.attemptsTo(BuscarProducto.conTermino(termino));
    }

    @Entonces("la PLP muestra productos para la busqueda realizada")
    public void laPlpMuestraProductosParaLaBusquedaRealizada() {
        String termino = shopper.recall("plpSearchTerm");
        shopper.attemptsTo(ValidarResultados.para(termino));
    }

    @Cuando("abre el primer articulo del catalogo")
    public void abreElPrimerArticuloDelCatalogo() {
        shopper.attemptsTo(SeleccionarPrimerProducto.deLaLista());
    }

    @Entonces("accede al detalle del articulo elegido desde la PLP")
    public void accedeAlDetalleDelArticuloElegidoDesdeLaPlp() {
        String producto = shopper.recall(SeleccionarPrimerProducto.PRODUCTO_SELECCIONADO);
        shopper.attemptsTo(
                WaitUntil.the(PdpPage.TITULO_PRODUCTO, isVisible())
                        .forNoMoreThan(Duration.ofSeconds(30)),
                Ensure.thatTheAnswerTo(CurrentUrlContains.text("/tienda/pdp/")).isTrue(),
                Ensure.thatTheAnswerTo("el detalle corresponde al articulo elegido",
                        TextoContiene.en(PdpPage.TITULO_PRODUCTO, producto)).isTrue()
        );
    }
}
