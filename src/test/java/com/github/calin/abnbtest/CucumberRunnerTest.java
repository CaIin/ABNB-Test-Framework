package com.github.calin.abnbtest;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", 
                 glue ="com.github.calin.abnbtest.steps",
                 plugin = {"pretty", "html:target/cucumber/bagbasics"})
public class CucumberRunnerTest {
    
}
