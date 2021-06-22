package com.github.calin.abnbtest.pages;

import com.github.calin.abnbtest.config.FrameworkContext;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Value;

/**
 * Base class for Page Objects.
 *
 * @author Calin Dinis
 */
@SuppressWarnings("unused")
public abstract class BasePage {

  @Value("${timeout.seconds}")
  int timeoutSeconds;

  protected final WebDriver webDriver;
  protected final FluentWait<WebDriver> wait;

  public BasePage(FrameworkContext context) {
    this.webDriver = context.getDriver();
    this.wait =
        new FluentWait<>(webDriver)
            .withTimeout(Duration.ofSeconds(context.getTimeout()))
            .pollingEvery(Duration.ofMillis(context.getPollingInterval()))
            .ignoreAll(
                Arrays.asList(
                    NoSuchElementException.class,
                    StaleElementReferenceException.class,
                    ElementClickInterceptedException.class));
  }

  /** @param url - link to the page */
  protected void navigate(String url) {
    webDriver.get(url);
  }

  /**
   * Click the WebElement identified.
   *
   * @param by element identifier
   * @throws org.openqa.selenium.TimeoutException - if element is not found withing timeout
   */
  protected void click(By by) {
    wait.until(
        wd -> {
          WebElement el = webDriver.findElement(by);
          if (el.isDisplayed() && el.isEnabled()) {
            el.click();
            return true;
          }
          return false;
        });
  }

  /**
   * Click the WebElement el.
   *
   * @param el WebElement to click.
   * @throws org.openqa.selenium.TimeoutException - if element is cannot be clicked within timeout
   */
  protected void click(final WebElement el) {
    wait.until(
        wd -> {
          if (el.isDisplayed() && el.isEnabled()) {
            el.click();
            return true;
          }
          return false;
        });
  }

  /**
   * Sends the keys to element identified by by.
   *
   * @param by element identifier
   * @param keys text to be sent
   * @throws org.openqa.selenium.TimeoutException - if element is not found withing timeout
   */
  protected void sendKeys(By by, CharSequence keys) {
    wait.until(
        wd -> {
          WebElement el = webDriver.findElement(by);
          if (el.isDisplayed() && el.isEnabled()) {
            el.sendKeys(keys);
            return true;
          }
          return false;
        });
  }

  /**
   * Get the visible (i.e. not hidden by CSS) text of this element, including sub-elements.
   *
   * @param by element identifier
   * @return the inner text
   */
  protected String getText(By by) {
    return wait.until(
        webDriver1 -> {
          WebElement el = webDriver.findElement(by);
          if (el.isDisplayed() && el.isEnabled() && !el.getText().isEmpty()) {
            return el.getText();
          }
          return null;
        });
  }

  /**
   * Get the visible (i.e. not hidden by CSS) text of this Web Element, including sub-elements.
   *
   * @param el Web Element
   * @return the inner text
   */
  protected String getText(final WebElement el) {
    return wait.until(
        webDriver1 -> {
          if (el.isDisplayed() && el.isEnabled() && !el.getText().isEmpty()) {
            return el.getText();
          }
          return null;
        });
  }

  /**
   * Instantiates a By Object from string. The value needs to have the format
   * <b>seletor-type->value</b> where <b>selector-type</b> can be one of: <i>css, xpath, id,
   * className, linkText, partialLinkText, tagName</i> and value is the string representation of the
   * element identifier
   *
   * @param value the identifier for element in string format
   * @return By selenium element identifier
   */
  protected By getByFor(String value) {
    String[] selectorDetails = value.split("->");
    switch (selectorDetails[0].toLowerCase()) {
      case "css":
        return By.cssSelector(selectorDetails[1]);
      case "xpath":
        return By.xpath(selectorDetails[1]);
      case "id":
        return By.id(selectorDetails[1]);
      case "classname":
        return By.className(selectorDetails[1]);
      case "linktext":
        return By.linkText(selectorDetails[1]);
      case "partiallinktext":
        return By.partialLinkText(selectorDetails[1]);
      case "tagname":
        return By.tagName(selectorDetails[1]);
      default:
        throw new UnsupportedOperationException(
            "Selector type: " + selectorDetails[0] + " not supported");
    }
  }

  /**
   * Executes the action for the noOfTimes
   *
   * @param noOfTimes - counter
   * @param action - action to be executed
   */
  protected void performActionFor(int noOfTimes, Runnable action) {
    for (int i = 0; i < noOfTimes; i++) {
      action.run();
    }
  }

  protected void scrollElementIntoView(By by) {
    ((JavascriptExecutor) webDriver)
        .executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(by));
  }

  /**
   * Searches for all elements identified by locator, extracts the text of the element and returns
   * the stream. If the element's text is empty, it is <b>not</b> returned.
   *
   * @param locator identifier for the list of Elements
   * @return a Java 8 Stream of the texts of the Elements identified
   */
  protected List<String> getElementsText(By locator) {
    try {
      return webDriver.findElements(locator).stream()
          .filter(WebElement::isDisplayed)
          .filter(WebElement::isEnabled)
          .map(WebElement::getText)
          .filter(Objects::nonNull)
          .filter(not(String::isEmpty))
          .collect(Collectors.toList());
    } catch (WebDriverException ignored) {
      // the list has been updated, so we get it again for best results.
      return getElementsText(locator);
    }
  }

  private static <T> Predicate<T> not(Predicate<T> t) {
    return t.negate();
  }
}
