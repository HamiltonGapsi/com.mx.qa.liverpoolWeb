package com.liverpool.qa.screenplay.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

public class PageTitleContains implements Question<Boolean> {

    private final String expected;

    private PageTitleContains(String expected) {
        this.expected = expected;
    }

    public static PageTitleContains text(String expected) {
        return new PageTitleContains(expected);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String title = BrowseTheWeb.as(actor).getDriver().getTitle();
        return title != null && title.toLowerCase().contains(expected.toLowerCase());
    }
}

