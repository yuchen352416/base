package com.yuchen.base.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
*
* ʱ�乤����
*
* @author lsy
* @date 2016��11��13�� ����00:46:57
*
*/
public class DateUtil {
	
    public static final String DATE = "yyyy-MM-dd";

    public static final String DATETIME = "yyyy-MM-dd HH:mm:ss.SSS";

    private DateUtil() {}

    public static int getYear(long date){
        Date now = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        return calendar.get(Calendar.YEAR);
    }

    public static int getMonth(long date){
        Date now = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        return calendar.get(Calendar.MONTH);
    }

    public static int getDay(long date){
        Date now = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     *
     * ���ظ�ʽ���ַ���
     *
     * @param date
     *  Ĭ�ϸ�ʽ   yyyy-MM-dd HH24:mm:ss.SSS
     * @return ��ʽ������ַ���
     */
    public static String dateToString(Date date) {
        return DateUtil.dateToString(date, DATETIME);
    }

    /**
     *
     * ���ظ�ʽ���ַ���
     *
     * @param date ʱ��
     * @param format ת�����ʽ
     * @return ��ʽ������ַ���
     */
    public static String dateToString(Date date, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     *
     * ����ʱ��
     *
     * @param date ʱ���ַ���
     *  Ĭ�ϸ�ʽ   yyyy-MM-dd HH24:mm:ss.SSS
     * @return ʱ��
     * @throws ParseException
     */
    public static Date stringToDate(String date) throws ParseException {
        return DateUtil.stringToDate(date, DATETIME);
    }

    /**
     *
     * ����ʱ��
     *
     * @param date ʱ���ַ���
     * @param format ʱ���ʽ
     * @return ʱ��
     * @throws ParseException
     */
    public static Date stringToDate(String date, String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        Date result = null;
        result = df.parse(date);
        return result;
    }

    /**
     *
     * ����ʱ���
     *
     * @param date ʱ���ַ���
     *  Ĭ�ϸ�ʽ   yyyy-MM-dd HH24:mm:ss.SSS
     * @return ʱ���
     * @throws ParseException ת���쳣
     */
    public static Long stringToTimestamp(String date) throws ParseException {
        return DateUtil.stringToTimestamp(date, DateUtil.DATETIME);
    }

    /**
     *
     * ����ʱ���
     *
     * @param date ʱ���ַ���
     * @param format ʱ���ʽ
     * @return ʱ���
     * @throws ParseException
     */
    public static Long stringToTimestamp(String date, String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        Long result = null;
        result = df.parse(date).getTime();
        return result;
    }

    /**
     *
     * ����ʱ���ַ���
     *
     * @param time ʱ���
     * @return ʱ���ַ���
     * @throws ParseException ת���쳣
     */
    public static String longToString(Long time) throws ParseException {
        DateFormat df = new SimpleDateFormat(DATETIME);
        return df.format(new Date(time));
    }

    /**
     *
     * ����ʱ���ַ���
     *
     * @param time ʱ���
     * @param format ��ʽ
     * @return ʱ��
     */
    public static String longToString(Long time, String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(time));
    }

    /**
     *
     * ����ʱ���
     *
     * @param dates
     * @return ʱ�������
     */
    public static Long[] dateToLong(Date[] dates) {
        Long[] result = new Long[dates.length];
        for(int i = 0; i < dates.length; i++) {
            result[i] =  dates[i].getTime();
        }
        return result;
    }
    /**
     * �ж�ʱ���ʽ ��ʽ����ΪYYYY-MM-dd
     * 2016-2-30 ����Ч��
     * 2016-2-29 ����Ч��
     * @param  str
     * @return
     */
    public static boolean isValidDate(String str) {
        DateFormat formatter = new SimpleDateFormat(DATE);
        try{
            Date date = (Date)formatter.parse(str);
            return str.equals(formatter.format(date));
        }catch(Exception e){
            return false;
        }
    }

    /**
     * ���������ַ���, ��ȡ ���
     *
     * @param str �����ַ���  2017-01-01
     * @return int 2017
     */
    public static int getYearByString(String str) {
        return getIntByDateString(str, 0);
    }

    /**
     * ���������ַ���, ��ȡ �·�
     *
     * @param str �����ַ���  2017-01-01
     * @return int 1
     */
    public static int getMonthByString(String str) {
        return getIntByDateString(str, 1);
    }

    /**
     * ���������ַ���, ��ȡ ��
     *
     * @param str �����ַ���  2017-01-01
     * @return int 1
     */
    public static int getDayByString(String str) {
        return getIntByDateString(str, 2);
    }

    private static int getIntByDateString(String date, int i){
        int result = 0;
        String[] arr = date.split(" ");
        arr = arr[0].split("-");
        if(arr.length < 3 || i >= 3) {
            return 0;
        }
        result = Integer.valueOf(arr[i]);
        return result;
    }


    public static long getDayDiscrepancy(long startDate, long endDate) {
        long result = 0;
        if (startDate >= endDate) { return result; }

        Calendar firstDay = Calendar.getInstance();
        Calendar lastDay = Calendar.getInstance();
        firstDay.setTime(new Date(startDate));
        lastDay.setTime(new Date(endDate));

        // ��������ܲ�ֵ
        result = ((lastDay.getTimeInMillis()) - (firstDay.getTimeInMillis())) / (1000 * 24 * 60 * 60);

        return result;

    }

    /**
     * �����²�<br/>
     *   ע��: endDate > startDate
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static int getMonthDiscrepancy(long startDate, long endDate) {
        int result = 0;
        if (startDate >= endDate) { return 0; }

        Calendar firstDay = Calendar.getInstance();
        Calendar lastDay = Calendar.getInstance();
        firstDay.setTime(new Date(startDate));
        lastDay.setTime(new Date(endDate));

        // ������ֹѭ������
        Calendar loopEndDay = calculateLoopEndOfDate(firstDay, lastDay);

        int month = firstDay.get(Calendar.MONTH);
        while (!firstDay.equals(loopEndDay)) {
            firstDay.add(Calendar.DAY_OF_MONTH, 1);
            if (month != firstDay.get(Calendar.MONTH)) {
                month = firstDay.get(Calendar.MONTH);
                result++;
            }
        }
        return result;
    }

    /**
     * ����ѭ����ֹ���� ����:��ʼ�գ�2011/03/17 ������ 2012/02/13 ,ѭ����ֹ���� 2012/01/17;
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private static Calendar calculateLoopEndOfDate(Calendar startDate, Calendar endDate) {
        int year = endDate.get(Calendar.YEAR);
        int month = endDate.get(Calendar.MONTH);
        int day = startDate.get(Calendar.DAY_OF_MONTH);
        int maxDaysInMonth = getMaxDaysOfMonth(new GregorianCalendar(year, month, 1));

        if (year > startDate.get(Calendar.YEAR)) {
            if (month == Calendar.JANUARY) {
                year -= 1;
                month = Calendar.DECEMBER;
            } else {
                if (day > maxDaysInMonth) {
                    month -= 1;
                    endDate.set(year, month, 1);
                    day = getMaxDaysOfMonth(new GregorianCalendar(year, month, 1));
                } else {
                    if (day > endDate.get(Calendar.DAY_OF_MONTH)) {
                        month -= 1;
                        endDate.set(year, month, 1);
                        maxDaysInMonth = getMaxDaysOfMonth(new GregorianCalendar(
                                year, month, 1));
                        ;
                        if (day > maxDaysInMonth) {
                            day = maxDaysInMonth;
                        }
                    }
                }
            }
        } else {
            if (day > maxDaysInMonth) {
                month -= 1;
                endDate.set(year, month, 1);
                day = getMaxDaysOfMonth(new GregorianCalendar(year, month, 1));
            } else {
                if (day > endDate.get(Calendar.DAY_OF_MONTH)) {
                    month -= 1;
                    endDate.set(year, month, 1);
                    maxDaysInMonth = getMaxDaysOfMonth(new GregorianCalendar(
                            year, month, 1));
                    if (day > maxDaysInMonth) {
                        day = maxDaysInMonth;
                    }
                }
            }
        }

        return new GregorianCalendar(year, month, day);
    }

    /**
     * ��ȡһ���������,��������Ƿ�Ϊ���� (��API�е� getMaximum(int field)���˽�,
     * date.getMaximum(Calendar.DAY_OF_MONTH)ȴ�����·ݵ��������)
     *
     * @param date
     * @return
     */
    private static int getMaxDaysOfMonth(GregorianCalendar date) {
        int month = date.get(Calendar.MONTH);
        int maxDays = 0;
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                maxDays = 31;
                break;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                maxDays = 30;
                break;
            case Calendar.FEBRUARY:
                if (date.isLeapYear(date.get(Calendar.YEAR))) {
                    maxDays = 29;
                } else {
                    maxDays = 28;
                }
                break;
        }
        return maxDays;
    }

    /**
     *
     * ���ڼ�����
     *
     * @param date
     * @param day
     * @return
     */
    public static long getAddDay(long date,int day) {
        long time = (1000 * 24 * 60 * 60);
        return date + time * (day-1);
    }

    /**
     *
     * ���ڼ�����
     *
     * @param date
     * @param month
     * @return
     */
    public static long getAddMonth(long date, int month) {
        Date now = new Date(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, month);
        return calendar.getTimeInMillis();
    }
}
