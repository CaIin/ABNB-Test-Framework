package com.github.calin.abnbtest.steps;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("unused")
public class Hooks extends SpringBaseStep {

    @After("@CloseBrowserTab")
    public void closeBrowesertab(){
        webDriver.close();
        webDriver.switchTo().window(testContext.getOriginalWindowHandle());
    }

}
