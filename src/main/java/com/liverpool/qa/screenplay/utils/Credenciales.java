package com.liverpool.qa.screenplay.utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.environment.SystemEnvironmentVariables;
import net.thucydides.model.util.EnvironmentVariables;

/**
 * Lee las credenciales definidas en serenity.conf dentro de environments.{ambiente}.
 */
public final class Credenciales {

    private final String email;
    private final String password;

    private Credenciales(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static Credenciales delAmbiente(String ambiente) {
        return new Credenciales(
                propiedad(ambiente, "user.email"),
                propiedad(ambiente, "user.password"));
    }

    public String email() {
        return email;
    }

    public String password() {
        return password;
    }

    private static String propiedad(String ambiente, String clave) {
        EnvironmentVariables variables = SystemEnvironmentVariables.currentEnvironmentVariables();
        String valor = variables.getProperty("environments." + ambiente + "." + clave);
        if (valor == null || valor.isBlank()) {
            valor = EnvironmentSpecificConfiguration.from(variables).getOptionalProperty(clave).orElse(null);
        }
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException(
                    "No se encontró 'environments." + ambiente + "." + clave + "' en serenity.conf");
        }
        return valor;
    }
}
