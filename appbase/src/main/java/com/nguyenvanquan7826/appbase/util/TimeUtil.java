package com.nguyenvanquan7826.appbase.util;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TimeUtil {
    public static final String FORMAT_DATETIME_SQL = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_DATE_SQL = "yyyy-MM-dd";
    public static final String FORMAT_DATE_TIME_VN = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DATE_VN = "dd/MM/yyyy";
    public static final String FORMAT_TIME = "HH:mm";

    @NonNull
    private static SimpleDateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format, Locale.getDefault());
    }

    public static Calendar textToCal(String dateTime, String format) {
        Calendar cal = Calendar.getInstance();
        try {
            if (dateTime != null) cal.setTime(getDateFormat(format).parse(dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static String calToText(Calendar calendar, String format) {
        return getDateFormat(format).format(calendar.getTime());
    }

    public static Calendar itemToCalendar(int year, int month, int dayOfMonth, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth, hour, minute, second);
        return calendar;
    }

    public static String textToText(String text, String currentFormat, String endFormat) {
        return calToText(textToCal(text, currentFormat), endFormat);
    }
}
