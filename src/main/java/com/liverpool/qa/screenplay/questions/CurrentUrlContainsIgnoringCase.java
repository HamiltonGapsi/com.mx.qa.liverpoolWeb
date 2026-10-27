package com.liverpool.qa.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class CurrentUrlContainsIgnoringCase implements Question<Boolean> {

    private final String expectedFragment;

    private CurrentUrlContainsIgnoringCase(String expectedFragment) {
        this.expectedFragment = expectedFragment;
    }

    public static CurrentUrlContainsIgnoringCase text(String expectedFragment) {
        return new CurrentUrlContainsIgnoringCase(expectedFragment);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String currentUrl = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        if (currentUrl == null) {
            return false;
        }
        String decodedUrl = URLDecoder.decode(currentUrl, StandardCharsets.UTF_8).toLowerCase();
        return decodedUrl.contains(expectedFragment.toLowerCase());
    }
}
