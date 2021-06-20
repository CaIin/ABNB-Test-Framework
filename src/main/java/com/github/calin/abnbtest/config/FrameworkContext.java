package com.github.calin.abnbtest.config;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Holds the Framework Instance instance
 */
@Component
public class FrameworkContext {
    @Autowired WebDriver webDriver;

    @Value("${timeout.seconds}") int timeout;
    @Value("${polling.milliseconds}") long pollingInterval;
    @Value("${throttle.milliseconds}") long throttleMillis;

    public WebDriver getDriver() {
        return webDriver;
    }

    public int getTimeout() {
        return timeout;
    }

    public long getPollingInterval() {
        return pollingInterval;
    }

    public long getThrottleMillis() {
        return throttleMillis;
    }
}
