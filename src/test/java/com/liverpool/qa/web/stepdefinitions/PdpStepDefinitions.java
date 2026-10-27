package com.liverpool.qa.web.stepdefinitions;

import com.liverpool.qa.screenplay.actors.UsuarioWeb;
import com.liverpool.qa.screenplay.questions.TextoContiene;
import com.liverpool.qa.screenplay.tasks.landing.AbrirLanding;
import com.liverpool.qa.screenplay.tasks.landing.BuscarProducto;
import com.liverpool.qa.screenplay.tasks.pdp.AbrirCarrito;
import com.liverpool.qa.screenplay.tasks.pdp.AgregarAlCarrito;
import com.liverpool.qa.screenplay.tasks.pdp.SeleccionarTalla;
import com.liverpool.qa.screenplay.tasks.pdp.ValidarBolsa;
import com.liverpool.qa.screenplay.tasks.plp.SeleccionarPrimerProducto;
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

public class PdpStepDefinitions {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    @Managed
    private WebDriver driver;

    private Actor shopper;

    @Before
    public void prepararActor() {
        shopper = UsuarioWeb.llamado("Shopper Web", driver);
    }

    @Dado("que el cliente busca {string} desde la landing de Liverpool")
    public void queElClienteBuscaDesdeLaLandingDeLiverpool(String termino) {
        shopper.attemptsTo(
                AbrirLanding.principal(),
                BuscarProducto.conTermino(termino)
        );
    }

    @Cuando("selecciona el primer producto de la lista de resultados")
    public void seleccionaElPrimerProductoDeLaListaDeResultados() {
        shopper.attemptsTo(SeleccionarPrimerProducto.deLaLista());
    }

    @Entonces("se muestra el detalle del producto seleccionado")
    public void seMuestraElDetalleDelProductoSeleccionado() {
        String producto = shopper.recall(SeleccionarPrimerProducto.PRODUCTO_SELECCIONADO);
        shopper.attemptsTo(
                WaitUntil.the(PdpPage.TITULO_PRODUCTO, isVisible()).forNoMoreThan(TIMEOUT),
                Ensure.thatTheAnswerTo("el título del PDP corresponde al producto seleccionado",
                        TextoContiene.en(PdpPage.TITULO_PRODUCTO, producto)).isTrue(),
                Ensure.that(PdpPage.BOTON_AGREGAR_CARRITO).isDisplayed()
        );
    }

    @Cuando("elige la primera talla disponible")
    public void eligeLaPrimeraTallaDisponible() {
        shopper.attemptsTo(SeleccionarTalla.primeraDisponible());
    }

    @Cuando("agrega el producto a su bolsa")
    public void agregaElProductoASuBolsa() {
        shopper.attemptsTo(AgregarAlCarrito.elProducto());
    }

    @Cuando("abre su bolsa de compras desde el header")
    public void abreSuBolsaDeComprasDesdeElHeader() {
        shopper.attemptsTo(AbrirCarrito.desdeElHeader());
    }

    @Entonces("su bolsa contiene al menos un producto")
    public void suBolsaContieneAlMenosUnProducto() {
        shopper.attemptsTo(ValidarBolsa.conProductos());
    }

    @Entonces("puede continuar con la compra")
    public void puedeContinuarConLaCompra() {
        shopper.attemptsTo(
                WaitUntil.the(PdpPage.BOTON_COMPRAR_AHORA, isVisible()).forNoMoreThan(TIMEOUT),
                Ensure.that(PdpPage.BOTON_COMPRAR_AHORA).isEnabled()
        );
    }
}