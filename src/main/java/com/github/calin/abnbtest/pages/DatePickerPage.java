package com.github.calin.abnbtest.pages;

import com.github.calin.abnbtest.config.FrameworkContext;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatePickerPage extends BasePage {

  @Value("${timeout.seconds}")
  int timeout;

  @Value("${locators.datePicker.dayTemplate}")
  String datePickerTemplateLocator;

  @Value("${locators.datePicker.nextMonthArrow}")
  String nextMonthArrowLocator;

  @Value("${dates.datePicker.dateFormat}")
  String dateFormat;

  @Autowired
  public DatePickerPage(FrameworkContext context) {
    super(context);
  }

  public void clickNextMonthArrow() {
    click(getByFor(nextMonthArrowLocator));
  }

  /**
   * Selects the Date from Date Picker. Will scroll the Date picker if necessary to find the date.
   *
   * @param date dat to select from Date Picker. Must include day of the month, the Month and year.
   */
  public void selectDate(Date date) {
    String formattedDate = new SimpleDateFormat(dateFormat).format(date);
    By dateLocator = getByFor(String.format(datePickerTemplateLocator, formattedDate));
    List<WebElement> dates = null;
    try {
      webDriver.manage().timeouts().implicitlyWait(25, TimeUnit.MILLISECONDS);
      while ((dates = webDriver.findElements(dateLocator)).isEmpty()) {
        clickNextMonthArrow();
      }
    } finally {
      webDriver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
      click(dateLocator);
    }
  }
}
