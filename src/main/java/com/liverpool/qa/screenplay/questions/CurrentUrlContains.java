package com.liverpool.qa.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class CurrentUrlContains implements Question<Boolean> {

    private final String expectedFragment;

    private CurrentUrlContains(String expectedFragment) {
        this.expectedFragment = expectedFragment;
    }

    public static CurrentUrlContains text(String expectedFragment) {
        return new CurrentUrlContains(expectedFragment);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String currentUrl = BrowseTheWeb.as(actor).getDriver().getCurrentUrl();
        return currentUrl != null && currentUrl.contains(expectedFragment);
    }
}

