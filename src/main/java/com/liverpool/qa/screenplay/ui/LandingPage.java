package com.liverpool.qa.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LandingPage {

    public static final Target BARRA_BUSQUEDA =
            Target.the("barra de búsqueda").locatedBy("//input[@aria-label='Buscar por producto, categoría y más...']");

    public static final Target BOTON_CATEGORIAS_HEADER = Target.the("Botón Categorías del header")
            .locatedBy("//button[contains(@data-testid, 'header-button-category')]");

    public static final Target OPCION_CATEGORIA_MUJER = Target.the("Categoría Mujer del menú")
            .locatedBy("//a[contains(@href, '/tienda/mujer')]");

    public static final Target OPCION_SUBCATEGORIA_TOTE = Target.the("Subcategoría Tote del menú")
            .locatedBy("//a[contains(@href, '/tienda/tote')]");




}
