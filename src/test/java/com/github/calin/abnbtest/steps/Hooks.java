package com.github.calin.abnbtest.steps;

import io.cucumber.java.After;

@SuppressWarnings("unused")
public class Hooks extends SpringBaseStep {

    @After("@CloseBrowserTab")
    public void closeBrowserTab(){
        try {
            webDriver.close();
            webDriver.switchTo().window(testContext.getOriginalWindowHandle());
        } catch (Exception ignored) {
            // not too much we can do at this point
        }
    }

}
