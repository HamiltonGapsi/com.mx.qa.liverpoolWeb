package com.liverpool.qa.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public final class PdpPage {

    public static final Target NOMBRE_PRODUCTO =
            Target.the("nombre de producto").locatedBy("#product-display-name");

    public static final Target PRODUCTO_TENDENCIA_1 =
            Target.the("primer producto en tendencia").locatedBy("(//section[@data-carousel-product-sku])[1]");

    public static final Target BOTON_AGREGAR_CARRITO =
            Target.the("boton agregar a carrito").locatedBy("//button[@data-testid='add-to-bag-button']");

    public static final Target ICONO_CARRITO =
            Target.the("icono del carrito").locatedBy("(//a[@aria-label='shopping cart'])[1]");

    public static final Target BOTON_COMPRAR_AHORA =
            Target.the("boton comprar ahora").locatedBy("//button[@data-testid='checkout-payment-summary-button']");

    public static final Target TITULO_PRODUCTO =
            Target.the("título del producto en PDP").locatedBy("//main//h1");

    public static final Target SELECTOR_TALLAS =
            Target.the("selector de tallas").locatedBy("//div[starts-with(@data-testid,'ml-radio-group-size-picker')]");

    public static final Target PRIMERA_TALLA_DISPONIBLE = Target.the("primera talla disponible")
            .locatedBy("(//div[starts-with(@data-testid,'ml-radio-group-size-picker')]//label[input[not(@disabled)]])[1]");

    public static final Target CONTENEDOR_BOLSA =
            Target.the("contenido de mi bolsa").locatedBy("//*[@data-testid='template-checkout']");

    public static final Target PRIMER_PRODUCTO_EN_BOLSA = Target.the("primer producto en la bolsa")
            .locatedBy("(//*[@data-testid='template-checkout']//a[starts-with(@data-testid,'ml-card-product-mybag-') and contains(@data-testid,'-image-link')])[1]");

    public static final Target TARJETAS_PRODUCTO_BOLSA = Target.the("productos en mi bolsa")
            .locatedBy("//*[starts-with(@data-testid,'ml-card-product-mybag-') and not(contains(@data-testid,'-image'))]");

    public static Target productoEnBolsa(String nombreProducto) {
        return Target.the("producto en la bolsa " + nombreProducto)
                .locatedBy("//*[@data-testid='template-checkout']//*[contains(normalize-space(text()), '{0}')]")
                .of(nombreProducto);
    }
}