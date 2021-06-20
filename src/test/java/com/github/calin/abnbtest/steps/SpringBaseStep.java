package com.github.calin.abnbtest.steps;

import com.github.calin.abnbtest.config.FrameworkContext;
import com.github.calin.abnbtest.pages.MoreFiltersPage;
import com.github.calin.abnbtest.pages.PropertyDetailsPage;
import com.github.calin.abnbtest.pages.SearchHeaderPage;
import com.github.calin.abnbtest.pages.SearchResultsPage;
import io.cucumber.java.AfterStep;
import io.cucumber.spring.CucumberContextConfiguration;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@CucumberContextConfiguration
@SpringBootTest
public class SpringBaseStep {
    
    @Autowired
    SearchHeaderPage searchHeaderPage;
    @Autowired
    TestContext testContext;
    @Autowired
    SearchResultsPage searchResultsPage;
    @Autowired
    WebDriver webDriver;
    @Autowired
    MoreFiltersPage moreFiltersPage;
    @Autowired
    PropertyDetailsPage propertyDetailsPage;

    
}
