package com.github.calin.abnbtest.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class WebDriverConfig {
  @Value("${timeout.seconds}")
  int timeout;

  @Bean(destroyMethod = "quit")
  @ConditionalOnProperty(name = "browser.remote", havingValue = "false")
  public WebDriver getLocalWebDriver(@Value("${browser.name}") String browserName) {
    switch (browserName.toLowerCase()) {
      case BrowserType.CHROME:
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        return configure(new ChromeDriver(chromeOptions));
      case BrowserType.FIREFOX:
        WebDriverManager.firefoxdriver().setup();
        return configure(new FirefoxDriver());
      case "microsoftedge":
      case "edge":
        WebDriverManager.edgedriver().setup();
        return configure(new EdgeDriver());
      case BrowserType.OPERA:
        WebDriverManager.operadriver().setup();
        return configure(new OperaDriver());
      case BrowserType.SAFARI:
        return configure(new SafariDriver());
      default:
        throw new UnsupportedOperationException(
            "The Requested Browser(" + browserName.toLowerCase() + ") is not supported");
    }
  }

  @Bean(destroyMethod = "quit")
  @ConditionalOnProperty(name = "browser.remote", havingValue = "true")
  public WebDriver getRemoteWebDriver(
      @Value("${browser.name}") String browserName,
      @Value("${remote.webdriver.address}") String remoteWebDriverAddress)
      throws MalformedURLException {
    Capabilities capabilities;
    switch (browserName.toLowerCase()) {
      case BrowserType.FIREFOX:
        WebDriverManager.firefoxdriver().setup();
        capabilities = DesiredCapabilities.firefox();
        break;
      case BrowserType.CHROME:
        WebDriverManager.chromedriver().setup();
        capabilities = DesiredCapabilities.chrome();
        break;
      default:
        throw new UnsupportedOperationException(browserName + " not supported in Selenium Hub");
    }
    RemoteWebDriver driver = new RemoteWebDriver(new URL(remoteWebDriverAddress), capabilities);
    driver.setFileDetector(new LocalFileDetector());
    return configure(driver);
  }

  private WebDriver configure(WebDriver webDriver) {
    webDriver.manage().window().maximize();
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ignored) {
    }
    webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    return webDriver;
  }

  @Component
  @SuppressWarnings("unused")
  static class QuitDriver implements DisposableBean {
    @Autowired WebDriver webDriver;

    @Override
    public void destroy() {
      webDriver.quit();
    }
  }
}
