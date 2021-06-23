package com.github.calin.abnbtest.steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

@SuppressWarnings("unused")
public class Hooks extends SpringBaseStep {

  @After(order = 0)
  public void failedStepScreenshot(Scenario scenario) {
    if (scenario.isFailed()) {
      scenario.attach(takeScreenshot(), "image/png", "screenshot");
    }
  }

  @After(value = "@CloseBrowserTabs", order = 1)
  public void closeBrowserTab() {
    try {
      webDriver.getWindowHandles().stream()
          .sorted()
          .skip(1)
          .forEach(
              handle -> {
                webDriver.switchTo().window(handle);
                webDriver.close();
              });
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @Attachment(value = "Failed Step Screenshot", type = "image/png")
  public byte[] takeScreenshot() {
    return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
  }
}
