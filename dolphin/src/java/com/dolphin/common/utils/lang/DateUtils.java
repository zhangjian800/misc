package com.dolphin.common.utils.lang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

//import com.ebao.foundation.core.Cst;
public class DateUtils {

  private static String[] optionDateFormats = new String[]{
      "yyyy-MM-dd HH:mm:ss.S a", "yyyy-MM-dd HH:mm:ssz", "yyyy-MM-dd HH:mm:ss",
      "yyyy-MM-dd HH:mm:ssa"}; // backwards

  // compatability

  private DateUtils() {
  }

  /**
   * get the year of a date
   * 
   * @param date
   *            Date the date which the date get from
   * @return int the year of the date
   */
  public static int getYear(java.util.Date date) {
    Calendar calendar = createCalendar();
    setCalTime(date, calendar);
    return calendar.get(Calendar.YEAR);
  }

  /**
   * get the last two numbers of the year
   * 
   * @param date
   *            the date
   * @return the short format year
   */
  public static String getShortYear(java.util.Date date) {
    String year = getYear(date) + "";
    int length = year.length();
    return year.substring(length - 2, length);
  }

  /**
   * get the month of a date
   * 
   * @param date
   *            Date the date which the month get from
   * @return int the month of the date
   */
  public static int getMonth(java.util.Date date) {
    Calendar calendar = createCalendar();
    setCalTime(date, calendar);
    return calendar.get(Calendar.MONTH) + 1;
  }

  /**
   * get the day of a date
   * 
   * @param date
   *            Date the date which the day get from
   * @return int the day of the date
   */
  public static int getDay(java.util.Date date) {
    Calendar calendar = createCalendar();
    setCalTime(date, calendar);
    return calendar.get(Calendar.DAY_OF_MONTH);
  }

  /**
   * get the hour of a date
   * 
   * @param date
   *            Date the date which the hour get from
   * @return int the hour of the date
   */
  public static int getHour(java.util.Date date) {
    Calendar calendar = createCalendar();
    setCalTime(date, calendar);
    return calendar.get(Calendar.HOUR_OF_DAY);
  }

  /**
   * get the minute of a date
   * 
   * @param date
   *            Date the date which the minute get from
   * @return int the minute of the date
   */
  public static int getMinute(java.util.Date date) {
    Calendar calendar = createCalendar();
    setCalTime(date, calendar);
    return calendar.get(Calendar.MINUTE);
  }

  /**
   * get the second of a date
   * 
   * @param date
   *            Date the date which the second get from
   * @return int the second of the date
   */
  public static int getSecond(java.util.Date date) {
    Calendar calendar = createCalendar();
    setCalTime(date, calendar);
    return calendar.get(Calendar.SECOND);
  }

  /**
   * add days to a date
   * 
   * @param oldDate
   *            Date the date to be added to
   * @param addDays
   *            int the day to be added
   * @return Date the result date
   */
  public static Date addDay(java.util.Date oldDate, int addDays) {
    Calendar calendar = createCalendar();
    setCalTime(oldDate, calendar);
    calendar.add(Calendar.DATE, addDays);
    return calendar.getTime();
  }

  /**
   * add hours to a date
   * 
   * @param oldDate
   *            Date the date to be added to
   * @param addHours
   *            int the hour to be added
   * @return Date the result date
   */
  public static Date addHour(java.util.Date oldDate, int addHours) {
    Calendar calendar = createCalendar();
    setCalTime(oldDate, calendar);

    calendar.add(Calendar.HOUR, addHours);
    return calendar.getTime();
  }

  private static void setCalTime(java.util.Date oldDate, Calendar cal) {
    cal.setTime(oldDate);
  }

  /**
   * add months to a date
   * 
   * @param oldDate
   *            Date the date to be added to
   * @param addMonths
   *            int the day to be added
   * @return Date the result date
   */
  public static Date addMonth(java.util.Date oldDate, int addMonths) {
    Calendar calendar = createCalendar();
    calendar.setTime(oldDate);
    calendar.add(Calendar.MONTH, addMonths);
    return calendar.getTime();
  }

  /**
   * add years to a date
   * 
   * @param oldDate
   *            Date the date to be added to
   * @param addYears
   *            int the day to be added
   * @return Date the result date
   */
  public static Date addYear(java.util.Date oldDate, int addYears) {
    Calendar calendar = createCalendar();
    calendar.setTime(oldDate);
    calendar.add(Calendar.YEAR, addYears);
    return calendar.getTime();
  }

  /**
   * round the year of a date
   * 
   * @param date
   *            Date the date to be rounded
   * @return Date the rounded date
   */
  public static Date roundYear(Date date) {
    return org.apache.commons.lang.time.DateUtils.round(date, Calendar.YEAR);
  }

  /**
   * round the month of a date
   * 
   * @param date
   *            Date the date to be rounded
   * @return Date the rounded date
   */
  public static Date roundMonth(Date date) {
    return org.apache.commons.lang.time.DateUtils.round(date, Calendar.MONTH);
  }

  /**
   * round the day of a date
   * 
   * @param date
   *            Date the date to be rounded
   * @return Date the rounded date
   */
  public static Date roundDay(Date date) {
    return org.apache.commons.lang.time.DateUtils.round(date, Calendar.DATE);
  }

  /**
   * round the hour of a date
   * 
   * @param date
   *            Date the date to be rounded
   * @return Date the rounded date
   */
  public static Date roundHour(Date date) {
    return org.apache.commons.lang.time.DateUtils.round(date, Calendar.HOUR);
  }

  /**
   * round the minute of a date
   * 
   * @param date
   *            Date the date to be rounded
   * @return Date the rounded date
   */
  public static Date roundMinute(Date date) {
    return org.apache.commons.lang.time.DateUtils.round(date, Calendar.MINUTE);
  }

  /**
   * round the second of a date
   * 
   * @param date
   *            Date the date to be rounded
   * @return Date the rounded date
   */
  public static Date roundSecond(Date date) {
    return org.apache.commons.lang.time.DateUtils.round(date, Calendar.SECOND);
  }

  /**
   * truncate the year of a date
   * 
   * @param date
   *            Date the date to be truncated
   * @return Date the truncated date
   */
  public static Date truncateYear(Date date) {
    return org.apache.commons.lang.time.DateUtils.truncate(date, Calendar.YEAR);
  }

  /**
   * truncate the month of a date
   * 
   * @param date
   *            Date the date to be truncated
   * @return Date the truncated date
   */
  public static Date truncateMonth(Date date) {
    return org.apache.commons.lang.time.DateUtils
        .truncate(date, Calendar.MONTH);
  }

  /**
   * truncate the day of a date
   * 
   * @param date
   *            Date the date to be truncated
   * @return Date the truncated date
   */
  public static Date truncateDay(Date date) {
    return org.apache.commons.lang.time.DateUtils.truncate(date, Calendar.DATE);
  }

  /**
   * truncate the hour of a date
   * 
   * @param date
   *            Date the date to be truncated
   * @return Date the truncated date
   */
  public static Date truncateHour(Date date) {
    return org.apache.commons.lang.time.DateUtils.truncate(date, Calendar.HOUR);
  }

  /**
   * truncate the minute of a date
   * 
   * @param date
   *            Date the date to be truncated
   * @return Date the truncated date
   */
  public static Date truncateMinute(Date date) {
    return org.apache.commons.lang.time.DateUtils.truncate(date,
        Calendar.MINUTE);
  }

  /**
   * truncate the second of a date
   * 
   * @param date
   *            Date the date to be truncated
   * @return Date the truncated date
   */
  public static Date truncateSecond(Date date) {
    return org.apache.commons.lang.time.DateUtils.truncate(date,
        Calendar.SECOND);
  }

  public static String date2String(java.util.Date date, String format) {
    if (date == null)
      return null;
    else {
      if (format == null)
        format = "dd/MM/yyyy";
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
      return simpleDateFormat.format(date);
    }
  }

  /**
   * convert a string to a date according to the indicated format.
   * 
   * @param sDate
   *            String the string to be transferred
   * @param format
   *            String the indicated format
   * @exception Exception
   *                if the specified string cannot be parsed.
   * @return Date the transferred date
   */
  public static java.util.Date toDate(String sDate, String format)
      throws Exception {
    if (sDate == null || sDate.equals(""))
      return null;
    // SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
    return parse(sDate, format);
  }

  //
  public static java.util.Date toDate(int year, int month, int date, int hrs,
      int min, int sec) {
    Calendar calendar = createCalendar();
    calendar.set(year, month - 1, date, hrs, min, sec);
    return calendar.getTime();
  }

  private static Date parse(String date, String defaultFormat) throws Exception {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultFormat);

    try {
      return simpleDateFormat.parse(date);
    } catch (ParseException e) {
      for (int i = 0; i < optionDateFormats.length; i++) {
        try {
          SimpleDateFormat format = new SimpleDateFormat(optionDateFormats[i]);
          return format.parse(date);
        } catch (ParseException e2) {
          // no worries, let's try the next format.
        }
      }
      // no dateFormats left to try
      if (null == date)
        throw new Exception("Cannot parse date <null>");
      else
        throw new Exception("Cannot parse date " + date);

    }

  }

  /**
   * calculate age according to the birthday
   * 
   * @param birthday
   *            Date the birthday to be converted
   * @param endDate
   *            Date the destination date
   * @return int the age
   */
  public static int getAge(Date birthday, Date endDate) {
    return (int) getYearAmount(birthday, endDate);
  }

  /**
   * calculate age according to the birthday
   * 
   * @param startDate
   *            Date the birthday to be converted
   * @param endDate
   *            Date the destination date
   * @return int the age
   */
  public static double getYearAmount(Date startDate, Date endDate) {
    return getMonthAmount(startDate, endDate) / 12.0;
  }

  /**
   * calculate the days between two dates
   * 
   * @param startDate
   *            Date the start date to be calculated
   * @param endDate
   *            Date the end date to be calculated
   * @return int the result day amount
   */
  public static double getDayAmount(Date startDate, Date endDate) {
    return ((endDate.getTime() - startDate.getTime()) / (double) (1000 * 60 * 60 * 24));
  }

  private static int DAYS_OF_MONTH = 30;
  /**
   * calculate the months between two dates calculational logic(refer to
   * months_between(date1,date2), a internal function of SQL): if they have
   * the same day of month or they both are the max day of their own month,
   * the result is a integer. Otherwise, the result contains decimal part, and
   * the days of a month defined by CONST.DAYS_OF_MONTH.
   * 
   * @param startDate
   *            Date the start date to be calculated
   * @param endDate
   *            Date the end date to be calculated
   * @return int the result month amount
   */
  public static double getMonthAmount(Date startDate, Date endDate) {
    int years = 0;
    int nonths = 0;
    double days = 0;
    double monthAmount = 0;
    years = getYear(endDate) - getYear(startDate);
    nonths = getMonth(endDate) - getMonth(startDate);
    if ((getDay(endDate) == getDay(startDate))
        || (isMaxDayOfMonth(startDate) && isMaxDayOfMonth(endDate))) {
      days = 0;
    } else {
      days = getDay(endDate) - getDay(startDate);
    }
    monthAmount = years * 12 + nonths + days / DAYS_OF_MONTH;
    return monthAmount;
  }

  /**
   * to judge whether the date is the max day of its month.
   * 
   * @param date
   *            Date the date
   * @return boolean true if the date is the max day of its month.
   */
  public static boolean isMaxDayOfMonth(Date date) {
    return getDay(date) == getMaxDayOfMonth(date);
  }

  /**
   * get the max day of the indicated month by the date
   * 
   * @param date
   *            Date the date to indicate the month
   * @return int the max day of the month
   */
  public static int getMaxDayOfMonth(Date date) {
    Calendar c = createCalendar();
    c.setTime(date);
    return c.getActualMaximum(Calendar.DAY_OF_MONTH);
  }

  public static boolean isWeekend(java.util.Date date) {
    Calendar cal = createCalendar();
    cal.setTime(date);
    int i = cal.get(Calendar.DAY_OF_WEEK);
    return isWeekend(i);

  }

  public static boolean isWeekend(int currDayOfWeek) {
    return currDayOfWeek == Calendar.SATURDAY
        || currDayOfWeek == Calendar.SUNDAY;

  }

  public static boolean isWeekend(String strDate) throws Exception {
    Date date = parse(strDate, "yyyyMMdd");
    return isWeekend(date);

  }

  /**
   * transform java.util.Date to java.sql.Date
   * 
   * @param utilDate
   *            Date
   * @return Date
   */
  public static java.sql.Date toSqlDate(Date utilDate) {
    if (utilDate == null)
      return null;
    return new java.sql.Date(utilDate.getTime());
  }

  public static int getAgeNearest(Date birthday, Date endDate) {
    return (int) Math.round(getYearAmount(birthday, endDate));

  }

  public static Calendar createCalendar() {
    Calendar result = null;
    /*AppUser user = null;
     try {
     user = AppUserContext.getCurrentUser();
     } catch (RTException ex) {
     if (ThreadBindResourceManager
     .hasBindedResource(WebConstant.NO_APPUSER_TRUE)) {
     }
     throw ex;
     }
     String organId = user.getOrganId();
     if (organId == null) {
     throw new RTException("Wrong organ id for user :" + user);
     }
     String timeZone = Para.getParaValueAlwaysToCache(
     ParaId.PUB_ORGNA_TIME_ZONE, organId, null, null);
     if (null == timeZone) {
     result = Calendar.getInstance();
     } else {
     TimeZone tz = TimeZone.getTimeZone(timeZone);
     // no use timezone now 
     result = Calendar.getInstance();
     }*/
    result = Calendar.getInstance();
    return result;
  }

}
