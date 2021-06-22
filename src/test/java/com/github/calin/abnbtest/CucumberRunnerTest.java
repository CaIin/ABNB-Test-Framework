package com.github.calin.abnbtest;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    glue = "com.github.calin.abnbtest.steps",
    plugin = {
      "pretty",
      "html:target/cucumber-html-reports/report.html",
      "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm",
      "rerun:target/failed_scenarios.txt"
    },
    monochrome = true)
public class CucumberRunnerTest {}
