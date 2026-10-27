package com.liverpool.qa.screenplay.ui;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;

public final class PlpPage {

    public static final Target GRILLA_RESULTADOS =
            Target.the("grilla de resultados").located(By.id("plp-page-card-product-list"));

    public static final Target LISTA_TARJETAS_PRODUCTO = Target.the("tarjetas de productos en catálogo")
            .locatedBy("#plp-page-card-product-list > a");

    public static Target productoPorNombre(String nombreProducto) {
        return Target.the("producto " + nombreProducto)
                .locatedBy("//*[@id='plp-page-card-product-list']//h3[contains(text(), '{0}')]")
                .of(nombreProducto);
    }

    public static Target tarjetaPorTestId(String testId) {
        return Target.the("tarjeta de producto por testid " + testId)
                .locatedBy("a[data-testid='{0}-card-card-link']")
                .of(testId);
    }

    public static final Target NOMBRE_PRIMER_PRODUCTO = Target.the("nombre del primer producto del catálogo")
            .locatedBy("(//*[@id='plp-page-card-product-list']/a//h3)[1]");



}
