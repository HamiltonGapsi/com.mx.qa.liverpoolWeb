package com.liverpool.qa.web.runners.functional;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

/**
 * Flujo funcional de la landing: búsqueda global, navegación del menú Categorías y acceso a login.
 */
@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        features = "src/test/resources/features/landing",
        glue = "com.liverpool.qa.web",
        tags = "(@busqueda-global or @navegacion-menu or @acceso-login or @inicio-sesion) and not @wip",
        plugin = {"pretty"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class LandingFlujoTestRunner {
}
