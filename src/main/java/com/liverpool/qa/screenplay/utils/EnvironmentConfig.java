package com.liverpool.qa.screenplay.utils;

public final class EnvironmentConfig {

    private static final String DEFAULT_ENVIRONMENT = "dev";
    private static final String DEFAULT_BASE_URL = "https://www.liverpool.com.mx";

    private EnvironmentConfig() {
    }

    public static String activeEnvironment() {
        String environment = firstNonBlank(
            System.getProperty("environment"),
            System.getenv("TEST_ENV"),
            DEFAULT_ENVIRONMENT
        );
        return environment.toLowerCase();
    }

    public static String baseUrl() {
        String baseUrl = firstNonBlank(
            System.getProperty("base.url"),
            System.getProperty(activeEnvironment() + ".base.url"),
            System.getenv((activeEnvironment() + "_BASE_URL").toUpperCase()),
            DEFAULT_BASE_URL
        );

        return sanitizeTrailingSlash(baseUrl);
    }

    public static String dataSourceType() {
        return firstNonBlank(
            System.getProperty("data.source.type"),
            System.getProperty(activeEnvironment() + ".data.source.type"),
            dataSourceTypeByEnvironment(activeEnvironment())
        );
    }

    public static String dataSourcePath() {
        return firstNonBlank(
            System.getProperty("data.source.path"),
            System.getProperty(activeEnvironment() + ".data.source.path"),
            dataSourcePathByEnvironment(activeEnvironment())
        );
    }

    public static String dbUrl() {
        return firstNonBlank(
            System.getProperty("db.url"),
            System.getenv((activeEnvironment() + "_DB_URL").toUpperCase()),
            ""
        );
    }

    public static String dbUser() {
        return firstNonBlank(
            System.getProperty("db.user"),
            System.getenv((activeEnvironment() + "_DB_USER").toUpperCase()),
            ""
        );
    }

    public static String dbPassword() {
        return firstNonBlank(
            System.getProperty("db.password"),
            System.getenv((activeEnvironment() + "_DB_PASSWORD").toUpperCase()),
            ""
        );
    }

    public static String dbSchema() {
        return firstNonBlank(
            System.getProperty("db.schema"),
            System.getenv((activeEnvironment() + "_DB_SCHEMA").toUpperCase()),
            ""
        );
    }

    private static String dataSourceTypeByEnvironment(String environment) {
        switch (environment) {
            case "qa":
                return "local-json";
            case "prod":
                return "gcp-bucket";
            case "dev":
            default:
                return "local-json";
        }
    }

    private static String dataSourcePathByEnvironment(String environment) {
        switch (environment) {
            case "qa":
                return "src/test/resources/data/test-data-qa.json";
            case "prod":
                return "https://storage.googleapis.com/your-bucket/test-data-prod.json";
            case "dev":
            default:
                return "src/test/resources/data/test-data-dev.json";
        }
    }

    private static String sanitizeTrailingSlash(String value) {
        if (value == null) {
            return "";
        }
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }

    private static String firstNonBlank(String... values) {
        for (String value : values) {
            if (value != null && !value.isBlank()) {
                return value;
            }
        }
        return "";
    }
}

