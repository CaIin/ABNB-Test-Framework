package com.github.calin.abnbtest.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Configuration
@SuppressWarnings("unused")
public class WebDriverConfig {
    @SuppressWarnings("unused")
    @Value("${timeout.seconds}")
    int timeout;

    @Bean(destroyMethod = "quit")
    @ConditionalOnProperty(name="browser.remote", havingValue = "false")
    public WebDriver getChromeDriver(@Value("${browser.name}") String browserName) {
        switch(browserName.toLowerCase()) {
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
                throw new UnsupportedOperationException("The Requested Browser(" + browserName.toLowerCase() + ") is not supported");
        }
    }

    private WebDriver configure(WebDriver webDriver) {
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        return webDriver;
    }


    @Component
    @SuppressWarnings("unused")
    static class QuitDriver implements DisposableBean {
        @Autowired
        WebDriver webDriver;

        @Override
        public void destroy() {
           webDriver.quit();
        }
    }

}
