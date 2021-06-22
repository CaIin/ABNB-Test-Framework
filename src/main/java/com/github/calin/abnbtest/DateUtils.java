package com.github.calin.abnbtest;

import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DateUtils {
  private static final Parser PARSER = new Parser();

  /**
   * Tries to identify a date from the freeform text. The date identified is then adjusted current
   * Date.
   *
   * @param text - free form text containing 1 Date
   * @return identified Date object or null if we could not identify any date
   */
  public static Date parseDateNaturalLanguage(String text) {
    for (DateGroup dateGroup : PARSER.parse(text)) {
      return dateGroup.getDates().get(0);
    }
    return null;
  }

  /**
   * Tries to identify a date from the freeform text. The date identified is then adjusted to
   * reference date
   *
   * @param text - free form text containing 1 Date
   * @param referenceDate - the date on which the date from text is relative to
   * @return identified Date object or null if we could not identify any date
   */
  public static Date parseDateNaturalLanguage(String text, Date referenceDate) {

    for (DateGroup dateGroup : PARSER.parse(text, referenceDate)) {
      return dateGroup.getDates().get(0);
    }
    return null;
  }

  public static List<Date> parseAllDatesFromText(String text) {
    List<Date> parsedDates = new ArrayList<>();
    for (DateGroup dateGroup : PARSER.parse(text)) {
      parsedDates.addAll(dateGroup.getDates());
    }
    return parsedDates;
  }

  public static List<Integer> getDaysOfTheYear(List<Date> dates) {
    return dates.stream().map(DateUtils::getDayOfTheYear).sorted().collect(Collectors.toList());
  }

  public static Integer getDayOfTheYear(Date date) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    return calendar.get(Calendar.DAY_OF_YEAR);
  }
}
