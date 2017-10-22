package com.aufahr.common.Util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by AufaHR on 10/22/2017.
 */
public class GeneralUtil {
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}
