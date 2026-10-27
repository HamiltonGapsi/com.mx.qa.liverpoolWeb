package com.liverpool.qa.web.stepdefinitions;

import com.liverpool.qa.screenplay.actors.UsuarioWeb;
import com.liverpool.qa.screenplay.questions.CurrentUrlContainsIgnoringCase;
import com.liverpool.qa.screenplay.questions.PageTitleContains;
import com.liverpool.qa.screenplay.questions.VistaCambio;
import com.liverpool.qa.screenplay.tasks.landing.AbrirLanding;
import com.liverpool.qa.screenplay.tasks.landing.AccederALogin;
import com.liverpool.qa.screenplay.tasks.landing.BuscarProducto;
import com.liverpool.qa.screenplay.tasks.landing.IniciarSesion;
import com.liverpool.qa.screenplay.tasks.landing.NavegarMenuCategorias;
import com.liverpool.qa.screenplay.ui.LoginPage;
import com.liverpool.qa.screenplay.ui.PlpPage;
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

public class LandingStepDefinitions {

    private static final Duration TIMEOUT = Duration.ofSeconds(30);

    @Managed
    private WebDriver driver;

    private Actor shopper;

    @Before
    public void prepararActor() {
        shopper = UsuarioWeb.llamado("Shopper Web", driver);
    }

    @Dado("que el cliente abre la landing de Liverpool")
    public void queElClienteAbreLaLandingDeLiverpool() {
        shopper.attemptsTo(AbrirLanding.principal());
    }

    @Cuando("consulta el titulo esperado de la landing")
    public void consultaElTituloEsperadoDeLaLanding() {
    }

    @Entonces("la landing se muestra con el contexto correcto")
    public void laLandingSeMuestraConElContextoCorrecto() {
    }

    @Cuando("el cliente busca el término {string} en la barra superior")
    public void elClienteBuscaElTerminoEnLaBarraSuperior(String termino) {
        shopper.attemptsTo(BuscarProducto.conTermino(termino));
    }

    @Entonces("el sistema redirige a la PLP con los artículos de {string}")
    public void elSistemaRedirigeALaPlpConLosArticulosDe(String termino) {
        shopper.attemptsTo(
                WaitUntil.the(PlpPage.GRILLA_RESULTADOS, isVisible()).forNoMoreThan(TIMEOUT),
                Ensure.that(PlpPage.GRILLA_RESULTADOS).isDisplayed(),
                Ensure.thatTheAnswerTo(CurrentUrlContainsIgnoringCase.text(termino)).isTrue()
        );
    }

    @Cuando("el cliente navega en el menú de Categorías por Mujer > Bolsas > Tote")
    public void elClienteNavegaEnElMenuDeCategoriasPorMujerBolsasTote() {
        shopper.attemptsTo(NavegarMenuCategorias.haciaMujerBolsasTote());
    }

    @Entonces("se carga la vista de la categoría {string}")
    public void seCargaLaVistaDeLaCategoria(String categoria) {
        shopper.attemptsTo(
                WaitUntil.the(PlpPage.GRILLA_RESULTADOS, isVisible()).forNoMoreThan(TIMEOUT),
                Ensure.thatTheAnswerTo(CurrentUrlContainsIgnoringCase.text("/tienda/" + categoria)).isTrue()
        );
    }

    @Cuando("el cliente da clic en {string} del banner personalizado")
    public void elClienteDaClicEnDelBannerPersonalizado(String opcion) {
        shopper.attemptsTo(AccederALogin.desdeElBanner());
    }

    @Entonces("se presenta el formulario de autenticación")
    public void sePresentaElFormularioDeAutenticacion() {
        shopper.attemptsTo(
                WaitUntil.the(LoginPage.CAMPO_USUARIO, isVisible()).forNoMoreThan(TIMEOUT),
                Ensure.that(LoginPage.CAMPO_USUARIO).isDisplayed(),
                Ensure.that(LoginPage.CAMPO_PASSWORD).isDisplayed(),
                Ensure.that(LoginPage.BOTON_INGRESAR).isDisplayed()
        );
    }

    @Cuando("inicia sesión con las credenciales del ambiente {string}")
    public void iniciaSesionConLasCredencialesDelAmbiente(String ambiente) {
        shopper.attemptsTo(IniciarSesion.conCredencialesDe(ambiente));
    }

    @Entonces("se carga una nueva vista después de iniciar sesión")
    public void seCargaUnaNuevaVistaDespuesDeIniciarSesion() {
        String urlLogin = shopper.recall(IniciarSesion.URL_LOGIN);
        shopper.attemptsTo(
                Ensure.thatTheAnswerTo("la vista cambió tras iniciar sesión", VistaCambio.desde(urlLogin, TIMEOUT))
                        .isTrue()
        );
    }
}