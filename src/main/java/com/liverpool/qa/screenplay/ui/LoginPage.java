package com.liverpool.qa.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class LoginPage {
    public static final Target INICIO_SESION =
            Target.the("inicio de sesion").locatedBy("//button[contains(@data-testid, 'header-menu-dropdown-button')]");

    public static final Target OPCION_INICIAR_SESION = Target.the("Opción Iniciar Sesión del menú desplegable")
            .locatedBy("//div[contains(@class, 'cursor-pointer') and .//span[contains(@data-testid, 'header-menu-dropdown-menu') and text()='account_circle'] and .//span[normalize-space()='Iniciar sesión']]");

    public static final Target OPCION_MIS_COMPRAS = Target.the("Opción Mis compras del menú de usuario")
            .locatedBy("//a[contains(@data-testid, 'header-menu-dropdown-button-item') and .//span[normalize-space()='Mis compras']]");

    public static final Target CAMPO_USUARIO =
            Target.the("campo de usuario").locatedBy("#username");

    public static final Target CAMPO_PASSWORD =
            Target.the("campo de contraseña").locatedBy("#password");

    public static final Target BOTON_INGRESAR =
            Target.the("boton para ingresar").locatedBy("//button[contains(text(),'Iniciar sesión')]");
}







