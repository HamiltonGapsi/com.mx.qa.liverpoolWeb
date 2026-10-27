package com.liverpool.qa.web.runners.functional;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberSerenityRunner;
import org.junit.runner.RunWith;

@RunWith(CucumberSerenityRunner.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.liverpool.qa.web",
        tags = "@plp and not @wip",
        plugin = {"pretty"}
)
public class PlpTestSuite {
}

