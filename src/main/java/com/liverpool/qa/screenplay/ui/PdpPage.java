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
}