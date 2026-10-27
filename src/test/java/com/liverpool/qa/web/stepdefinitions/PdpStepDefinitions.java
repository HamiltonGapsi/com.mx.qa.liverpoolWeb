package com.liverpool.qa.web.stepdefinitions;

import com.liverpool.qa.screenplay.actors.UsuarioWeb;
import com.liverpool.qa.screenplay.questions.CurrentUrlContains;
import com.liverpool.qa.screenplay.tasks.pdp.AbrirPdpPorSku;
import com.liverpool.qa.screenplay.tasks.pdp.SolicitarAgregarCarrito;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.openqa.selenium.WebDriver;

public class PdpStepDefinitions {

    @Managed
    private WebDriver driver;

    private Actor shopper;

    @Before
    public void prepararActor() {
        shopper = UsuarioWeb.llamado("Shopper Web", driver);
    }

    @Dado("que el cliente abre un PDP con datos externos")
    public void queElClienteAbreUnPdpConDatosExternos() {
        // Leemos el dato externo y lo guardamos
        String skuExterno = System.getProperty("sku", "123456");
        shopper.remember("pdpSku", skuExterno);

        // El actor abre la URL
        shopper.attemptsTo(AbrirPdpPorSku.conSku(skuExterno));
    }

    @Cuando("solicita agregar el producto al carrito")
    public void solicitaAgregarElProductoAlCarrito() {
        // El actor recupera el SKU de su memoria
        String sku = shopper.recall("pdpSku");

        // Ejecuta la Tarea que mapeamos (Clic en carrusel, agregar, ir a carrito, comprar)
        shopper.attemptsTo(SolicitarAgregarCarrito.deSku(sku));
    }

    @Entonces("se conserva el SKU objetivo para mapear el flujo real")
    public void seConservaElSkuObjetivoParaMapearElFlujoReal() {
        String sku = shopper.recall("pdpSku");
        String requestedSku = shopper.recall("skuAddToCartRequested");

        // 1. Validamos que el SKU solicitado se haya registrado en la memoria del actor
        if (sku != null) {
            shopper.attemptsTo(Ensure.that(requestedSku).isEqualTo(sku));
        } else {
            // Si el flujo vino del carrusel (sin SKU explicito), aseguramos que requestedSku tenga un valor
            shopper.attemptsTo(Ensure.that(requestedSku).isNotNull());
        }

    }
}