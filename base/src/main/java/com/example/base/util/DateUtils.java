/*
 *  Copyright 2014-2015 snakerflow.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.example.base.util;



import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author yuqs
 * @since 0.1
 */
public class DateUtils {
    private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

    public static String getCurrentTime() {
        return new DateTime().toString(DATE_FORMAT_DEFAULT);
    }

    public static String getCurrentDay() {
        return new DateTime().toString(DATE_FORMAT_YMD);
    }
    
    public static String getEnDate(String zhDateStr) {
    	Date date = parseDate(zhDateStr, "yyyy-MM-dd");
    	SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
    	return sdf.format(date);
    }
    public static String getEnMonth(String zhDateStr) {
    	Date date = parseDate(zhDateStr, "yyyy-MM");
    	SimpleDateFormat sdf = new SimpleDateFormat("MMM yy", Locale.ENGLISH);
    	return sdf.format(date);
    }

    /**
     * 得到当前 天数
     *
     * @return int
     * @author lifq
     * @date 2015-12-3 上午11:40:40
     */
    public static int getCurrentDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }


    //得到当前月份
    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    //得到当前年份
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    //获得日期的年份
    public static int getCurrentYear(Date date) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        return dateCal.get(Calendar.YEAR);
    }

    //获得日期的年份
    public static int getCurrentMonth(Date date) {
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        return dateCal.get(Calendar.MONTH) + 1;
    }

    //得到当前月份最大的天数
    public static int getDayOfMonth() {
        int day = Calendar.getInstance().getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * 得到当前日期-格式YYYYMMdd 返回String类型
     */
    public static String getNowDateString() throws Exception {
        String dateString = "";
        String year = Calendar.getInstance().get(Calendar.YEAR) + "";
        String month = Calendar.getInstance().get(Calendar.MONTH) + "";
        Integer monthTemp = Integer.parseInt(month) + 1;
        String monthReslut = "";
        if (monthTemp < 10) {
            monthReslut = "0" + (monthTemp + "");
        } else {
            monthReslut = monthTemp.toString();
        }

        String day = Calendar.getInstance().get(Calendar.DATE) + "";
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }

        dateString = year + monthReslut + day;
        return dateString;
    }

    /***
     * 计算传入日期的往后顺延N天以后的日期对象
     *
     * @param date
     *            传入的日期对象
     * @param n
     *            往后顺延的天数
     * @return 顺延后的日期对象
     */
    public static Date getPreviousNDate(Date date, long n) {
        if (date == null) {
            return null;
        }
        long time = date.getTime();
        // 用毫秒数来计算新的日期
        time = time - n * 24 * 60 * 60 * 1000;
        return new Date(time);
    }

    public static String getPreviousNDate(String input, long n) {
        if (input == null) {
            return null;
        }
        Date date = getDate(input);
        long time = date.getTime();
        // 用毫秒数来计算新的日期
        time = time - n * 24 * 60 * 60 * 1000;
        return getYYYYMMDD(new Date(time));
    }

    public static String getAffterNDate(String input, long n) {
        if (input == null || "".equals(input)) {
            return null;
        }
        Date date = getDatetwo(input);
        long time = date.getTime();
        // 用毫秒数来计算新的日期
        time = time + n * 24 * 60 * 60 * 1000;
        return getYYYYMMDD(new Date(time));
    }

    /***
     * 获取传入日期下n年+1天的日期对象 
     *
     * @param date
     *            传入的日期对象 
     * @return 下n年的日期对象
     */
    public static Date getNextNYear(Date date, int n) {
        return new Date(date.getYear() + n, date.getMonth(), date.getDate());
    }

    /**
     * 获取传入日期下n年-1天的日期对象
     *
     * @return Date
     * @author lifq
     * @date 2015-9-1 上午10:01:47
     */
    public static Date getNextNYearMinusOne(Date date, int n) {
        return new Date(date.getYear() + n, date.getMonth(), date.getDate() - 1);
    }


    public static Date getNextNMonthDay(Date date) {
        int year = Integer.parseInt(getYYYYMMDD(date).substring(0, 4));
        int month = date.getMonth() + 1;
        int day = date.getDate();
        if (month == 1 && (day == 29 || day == 30 || day == 31)) {
            day = 28;
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
                day = 29;
        }
        if ((month == 3 || month == 5 || month == 8 || month == 11) && day == 31) {
            day = 30;
        }
        return new Date(date.getYear(), month, day);
    }

    /**
     * 下一年的今天
     *
     * @param date yyyy-MM-dd
     * @return
     */
    public static String getNextYearDay(Date date) {
        String dateStr = getYYYYMMDD(date);
        String next_year = null;
        try {
            int year = Integer.parseInt(dateStr.substring(0, 4));
            next_year = (year + 1) + dateStr.substring(4, dateStr.length());
            if (dateStr.substring(4, dateStr.length()).equals("-02-29")) {
                next_year = (year + 1) + "-02-28";
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return next_year;
    }


    /*** 
     * 获取传入日期下n月的日期对象 
     *
     * @param date
     *            传入的日期对象 
     * @return 下n年月的日期对象
     */
    public static Date getNextNMonth(Date date, int n) {
        return new Date(date.getYear(), date.getMonth() + n, date.getDate() - 1);
    }

    /**
     * 获取传入日期下n天的日期对象
     *
     * @param date
     * @param n
     * @return
     */
    public static String getNextNDay(String date, int n) {
        df.applyPattern(DATE_FORMAT_YMD);
        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(df.parse(date));
            cal.add(Calendar.DATE, n);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(cal.getTime());
    }

    public static String getDateStrByWeek() {
        SimpleDateFormat format = new SimpleDateFormat("yyw");
        return format.format(Calendar.getInstance().getTime());
    }

    public static String getDateStrByMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyMM");
        return format.format(Calendar.getInstance().getTime());
    }

    public static Date getDateByMonth(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yy-MM");
        Date parse1 = null;
        try {
           parse1 = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse1;
    }

    /**
     * 获取"yyyy-MM"格式的当前日期
     *
     * @return String"yyyy-MM"
     * @date 2017年7月13日 下午3:10:37
     */
    public static String getDateStrByMonth2() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(Calendar.getInstance().getTime());
    }

    public static String getDateStrByMonth(String dataFormat) {
        SimpleDateFormat format = new SimpleDateFormat(dataFormat);
        return format.format(Calendar.getInstance().getTime());
    }

    /**
     * 将String类型的日期按照指定格式转换为Date类型。<br />
     * 注意：如果转换失败会返回null，且不会抛出任何异常，请慎用此方法。
     *
     * @param in     String类型的日期
     * @param format 日期的格式
     * @return Date格式的日期。
     */
    public static Date parseDate(String in, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        try {
            return df.parse(in);
        } catch (ParseException e) {
        }

        return null;
    }

    public static String getDateStr(Date in, String format) {
        if (in == null) {
            return "";
        }

        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(in);
    }

    public static String getDateStr(String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    public static Date addOneDay(Date d) {
        if (d == null)
            return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    private static String secondStr = "秒";
    private static String minStr = "分";
    private static String hourStr = "小时";

    public static String getDateGap(Date s, Date e) {
        if (s == null || e == null)
            return "";
        long slong = s.getTime();
        long elong = e.getTime();
        if (slong >= elong)
            return "0" + secondStr;
        int sec = (int) ((elong - slong) / 1000);
        if (sec < 60)
            return sec + secondStr;
        int min = (int) (sec / 60);
        sec = sec - min * 60;
        if (min < 60)
            return min + minStr + sec + secondStr;
        int hour = (int) (min / 60);
        min = min - hour * 60;
        return hour + hourStr + min + minStr + sec + secondStr;
    }

    public static Date getDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDateCommen(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDatetwo(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    static SimpleDateFormat df = new SimpleDateFormat();

    public static String getYYYYMMDD(Date date) {
        if (date == null) {
            return "";
        }
        df.applyPattern("yyyy-MM-dd");
        return df.format(date);
    }

    
    public static String getYYYYMM(Date date) {
        if (date == null) {
            return "";
        }
        df.applyPattern("yyyy-MM");
        return df.format(date);
    }
    
    
    public static String getStrDate(Date date, String formDate) {
        if (date == null) {
            return "";
        }
        df.applyPattern(formDate);
        return df.format(date);
    }

    public static String getFormatDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(sdf.parse(date));
    }

    /**
     * 根据出生日期 得到年龄
     *
     * @param birth yyyy-MM-dd
     * @return
     */
    public static int getAge(String birth) {
        Date nowDate = new Date();
        Date birthDate = getDatetwo(birth);
        Calendar flightCal = Calendar.getInstance();
        flightCal.setTime(nowDate);
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthDate);
        int y = flightCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        int m = flightCal.get(Calendar.MONTH) - birthCal.get(Calendar.MONTH);
        int d = flightCal.get(Calendar.DATE) - birthCal.get(Calendar.DATE);
//		if(y<0){
//			throw new RuntimeException("您老还没出生");
//		}
        if (m < 0) {
            y--;
        }
        if (m == 0 && d < 0) {
            y--;
        }
        
        return y < 0 ? 0 : y;
    }

    /**
     * 根据年龄或司龄反推时间
     * @param birth
     * @return
     */
    public static String[] getAgeBack(int date) {
        String[] s = new String[2];
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -date);
        Date y = c.getTime();
        //范围开始时间
        String beginDate = format.format(y);
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(new Date());
        cEnd.add(Calendar.YEAR, -(date+1));
        Date yEnd = cEnd.getTime();
        String endDate = format.format(yEnd);
        //范围结束时间
        endDate = getSpecifiedDayAfter(endDate);
        s[0] = beginDate;
        s[1] = endDate;
        return s;
    }

    /**
     * 是否满一年
     *
     * @param work_date
     * @param year
     * @return
     */
    public static boolean isFullOneYear(String work_date, int year) {
        boolean b = false;
        if (StringUtils.isNotEmpty(work_date)) {
            String[] dates = work_date.split("-");
            int year_work = Integer.parseInt(dates[0]);
            int month = Integer.parseInt(dates[1]);
            int day = Integer.parseInt(dates[2]);
            int result = year - year_work;
            if (result > 1) {
                b = true;
            }
            if (result == 1) {
                if (month == 1 && day == 1) {
                    b = true;
                }
            }
        }
        return b;
    }

    /**
     * 根据出生日期 得到年龄
     */
    public static int getAge(Date birthDate) {
        Date nowDate = new Date();
        Calendar flightCal = Calendar.getInstance();
        flightCal.setTime(nowDate);
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(birthDate);
        int y = flightCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        int m = flightCal.get(Calendar.MONTH) - birthCal.get(Calendar.MONTH);
        int d = flightCal.get(Calendar.DATE) - birthCal.get(Calendar.DATE);
        if (y < 0) {
            throw new RuntimeException("您老还没出生");
        }
        if (m < 0) {
            y--;
        }
        if (m >= 0 && d < 0) {
            y--;
        }
        return y < 0 ? 0 : y;
    }

    public static String getMonthAddNum(String month, int num) throws ParseException {
        Calendar cal = Calendar.getInstance();
        df.applyPattern("yyyy-MM");
        cal.setTime(df.parse(month));
        cal.add(Calendar.MONTH, num);
        df.applyPattern("yyyy-MM-dd");
        return df.format(cal.getTime());
    }


    //得到上月 yyyy-MM-dd
    public static String getMonthAddNum(int num) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, num);
        df.applyPattern("yyyy-MM-dd");
        return df.format(cal.getTime());
    }

    //得到上月 yyyy-MM-dd
    public static String getMonthTimeAddNum(int num) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, num);
        df.applyPattern("yyyy-MM-dd HH:mm:ss");
        return df.format(cal.getTime());
    }

    //日期相差的天数
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    //日期相差的天数
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        if (StringUtils.isNotEmpty(smdate) && StringUtils.isNotEmpty(bdate)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date smdates = sdf.parse(smdate);
            Date bdates = sdf.parse(bdate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(smdates);
            long time1 = cal.getTimeInMillis();
            cal.setTime(bdates);
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000 * 3600 * 24);
            return Integer.parseInt(String.valueOf(between_days));
        } else {
            return 0;
        }
    }

    /**
     * 计算两个时间的差值（<i>bdate</i> 减去 <i>smdate</i>）。
     *
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static long timesBetween(Date smdate, Date bdate)
            throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();

        return time2 - time1;
    }

    //计算距离生日的天数
    public static int birthdayToNow(String clidate) throws ParseException {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cToday = Calendar.getInstance(); // 存今天
        Calendar cBirth = Calendar.getInstance(); // 存生日
        cBirth.setTime(myFormatter.parse(clidate)); // 设置生日
        cBirth.set(Calendar.YEAR, cToday.get(Calendar.YEAR)); // 修改为本年
        int days;
        if (cBirth.get(Calendar.DAY_OF_YEAR) < cToday.get(Calendar.DAY_OF_YEAR)) {
            // 生日已经过了，要算明年的了
            days = cToday.getActualMaximum(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
            days += cBirth.get(Calendar.DAY_OF_YEAR);
        } else {
            // 生日还没过
            days = cBirth.get(Calendar.DAY_OF_YEAR) - cToday.get(Calendar.DAY_OF_YEAR);
        }
        // 输出结果

//        if (days == 0) {
//            System.out.println("今天生日");
//        } else {
//            System.out.println("距离生日还有：" + days + "天");
//        }
        return days;
    }

    // 获得当前月--开始日期
    public static String getMinMonthDate(String date) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat1.parse(date));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            return dateFormat1.format(calendar.getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return null;

    }


    /**
     * 获取 当前月 -结束日期
     *
     * @return String
     * @author lifq
     * @date 2015-10-19 上午10:23:11
     */
    public static String getMaxMonthDate(String date) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(dateFormat1.parse(date));
            calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            return dateFormat1.format(calendar.getTime());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 根据日期获取星期几
     *
     * @param dateStr
     * @return
     * @throws Exception String
     * @throws
     * @author Singyh Hsu
     * @date 2016年6月16日 下午5:19:31
     * @Title getWeekOfDate
     * @Description
     */
    public static String getWeekOfDate(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysCode[intWeek];
    }

    /**
     * 根据日期获取星期几
     *
     * @param dateStr
     * @return
     * @throws Exception String
     * @throws
     * @author Singyh Hsu
     * @date 2016年6月16日 下午5:19:31
     * @Title getWeekOfDate
     * @Description
     */
    public static String getWeekNameOfDate(String dateStr) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateStr);
        String[] weekDaysName = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        String[] weekDaysCode = {"0", "1", "2", "3", "4", "5", "6"};
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return weekDaysName[intWeek];
    }

    public static Date getLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 获得考勤月份。（请勿在薪资计算中使用此方法）
     *
     * @param taDate  当前日期。
     * @param pattern 当前日期的格式。
     * @param rp      返回月份格式。
     * @param cs      考勤开始日。
     * @return 考勤月份
     */
    public static String getCurrencyMonth(String taDate, String pattern, String rp, Integer cs) {
        Calendar cal = Calendar.getInstance();

        try {
            cal.setTime(new SimpleDateFormat(pattern).parse(taDate));
            int d = cal.get(Calendar.DATE);
            //薪资月份（当前审批日期大于考勤开始日期则算作下月薪资月份）
            if (d >= cs) {
                cal.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat(rp).format(cal.getTime());
    }

    public static Map<String, String> transformationTime(String start_time, String end_time) {
        Map<String, String> time = new HashMap<String, String>();
        if ("am".equals(end_time)) {
            end_time = "12:30";
        } else if ("pm".equals(end_time) || "full".equals(end_time)) {
            end_time = "17:30";
        }
        time.put("end_time", end_time);
        if ("pm".equals(start_time)) {
            start_time = "13:30";
        } else if ("am".equals(start_time) || "full".equals(start_time)) {
            start_time = "08:30";
        }
        time.put("start_time", start_time);

        return time;
    }

    public static String showSet(String time) {
        if ("am".equals(time)) {
            return "AM";
        } else if ("pm".equals(time)) {
            return "PM";
        } else if ("full".equals(time)) {
            return "Full Day";
        }

        return time;
    }

    public static String showSet_bz(Integer time) {
        if (1 == time) {
            return "AM";
        } else if (2 == time) {
            return "PM";
        } else if (0 == time) {
            return "Full Day";
        }

        return time.toString();
    }

    public static String showReplace(String time, int languageTypeInt) {
        if (languageTypeInt == 1) {
            if (time != null) {
                if (time.indexOf("am") > -1) {
                    return time.replaceAll("am", "上午");
                } else if (time.indexOf("pm") > -1) {
                    return time.replaceAll("pm", "下午");
                } else if (time.indexOf("full") > -1) {
                    return time.replaceAll("full", "全天");
                }
            }
        } else {
            if (time != null) {
                if (time.indexOf("am") > -1) {
                    return time.replaceAll("am", "AM");
                } else if (time.indexOf("pm") > -1) {
                    return time.replaceAll("pm", "PM");
                } else if (time.indexOf("full") > -1) {
                    return time.replaceAll("full", "Full Day");
                }
            }
        }

        return time;
    }

    //计算两个日期相差天数，除去周末
    public static int getDutyDays(String strStartDate, String strEndDate) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            startDate = df.parse(strStartDate);
            endDate = df.parse(strEndDate);
        } catch (ParseException e) {
            System.out.println("非法的日期格式,无法进行转换");
            e.printStackTrace();
        }
        int result = 0;
        while (startDate.compareTo(endDate) <= 0) {
            if (startDate.getDay() != 6 && startDate.getDay() != 0)
                result++;
            startDate.setDate(startDate.getDate() + 1);
        }
        return result;
    }

    //比较两个日期大小

    public static int compare_date(String DATE1, String DATE2) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 比较两个日期大小
     *
     * @param date1   data1
     * @param date2   date2
     * @param pattern 格式
     * @return -1:小于,0:等于,1:大于
     */
    public static int compareDateByPattern(String date1, String date2, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取某年某月的第一天
     *
     * @param year
     * @param month
     * @return yyyy-MM-dd
     */
    public static String getFirstDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 获取某年某月的最后一天
     *
     * @param year
     * @param month
     * @return yyyy-MM-dd
     */
    public static String getLastDayOfMonth(int year, int month) {
        month = month - 1;
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 判断时间是否在指定区间内
     *
     * @param set_start_date 指定开始时间
     * @param set_end_date   指定结束时间
     * @param is_start_date  待判断时间
     * @return
     */
    public static boolean isAcrossDateArea(Date set_start_date, Date set_end_date, Date is_inner_date) {
        if ((is_inner_date.getTime() - set_start_date.getTime() >= 0) && (is_inner_date.getTime() - set_end_date.getTime() <= 0)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * dateStr1早于dateStr2 返回true
     *
     * @param dateStr1
     * @param dateStr2
     * @param format
     * @return
     */
    public static boolean isBefore(String dateStr1, String dateStr2, String format) {
        Date date1 = parseDate(dateStr1, format);
        Date date2 = parseDate(dateStr2, format);
        return date1.before(date2);
    }

    /**
     * 计算工作年限数，不满半年或半年按0.5年，满半年不满一年按1年。
     *
     * @param date1 开始日期yyyy-MM-dd
     * @param date2 结束日期yyyy-MM-dd
     * @return 工作年限
     */
    public static double getWorkYears(String datestr1, String datestr2) {
        double result = 0;
        Date date1 = parseDate(datestr1, "yyyy-MM-dd");
        Calendar date1Cal = Calendar.getInstance();
        date1Cal.setTime(date1);
        Date date2 = parseDate(datestr2, "yyyy-MM-dd");
        Calendar date2Cal = Calendar.getInstance();
        date2Cal.setTime(date2);
        int y = date2Cal.get(Calendar.YEAR) - date1Cal.get(Calendar.YEAR);
        int m = date2Cal.get(Calendar.MONTH) - date1Cal.get(Calendar.MONTH);
        if (compare_date(datestr2, datestr1) == 1) {//结束日期大于开始日期
            if (m == 0) {//结束日期的月份等于开始日期的月份
                Date newdate1 = getNextNMonth(date1, y * 12);
                int compareVal = compare_date(getDateStr(newdate1, "yyyy-MM-dd"), datestr2);
                if (compareVal == 1 || compareVal == 0) {//不满整年，年数-0.5
                    result = y;
                } else {//超过整年，年数+0.5
                    result = y + 0.5;
                }
            } else {//结束日期的月份不等于开始日期的月份
                if (m < 0) {//结束日期的月份小于开始日期的月份，年份需减1
                    y = y - 1;
                }
                Date newdate1 = getNextNMonth(date1, y * 12 + 6);//计算半年之后的日期，与结束日期进行比较
                int compareVal = compare_date(getDateStr(newdate1, "yyyy-MM-dd"), datestr2);
                if (compareVal == 1) {//不足半年，按0.5计算
                    result = y + 0.5;
                } else {//超过半年或等于半年，按1计算
                    result = y + 1;
                }
            }
        }
        return result;
    }
    /**
     * 计算工作年限数，不满一年按1年。
     *
     * @param date1 开始日期yyyy-MM-dd
     * @param date2 结束日期yyyy-MM-dd
     * @return 工作年限
     */
    public static double getWorkYearsTwo(String datestr1, String datestr2) {
        double result = 0;
        Date date1 = parseDate(datestr1, "yyyy-MM-dd");
        Calendar date1Cal = Calendar.getInstance();
        date1Cal.setTime(date1);
        Date date2 = parseDate(datestr2, "yyyy-MM-dd");
        Calendar date2Cal = Calendar.getInstance();
        date2Cal.setTime(date2);
        int y = date2Cal.get(Calendar.YEAR) - date1Cal.get(Calendar.YEAR);
        int m = date2Cal.get(Calendar.MONTH) - date1Cal.get(Calendar.MONTH);
        if (compare_date(datestr2, datestr1) == 1) {//结束日期大于开始日期
            if (m == 0) {//结束日期的月份等于开始日期的月份
                Date newdate1 = getNextNMonth(date1, y * 12);
                int compareVal = compare_date(getDateStr(newdate1, "yyyy-MM-dd"), datestr2);
                if (compareVal == 1 || compareVal == 0) {//不满整年，按1年算
                    result = y;
                } else {//超过整年，年数+1
                    result = y + 1;
                }
            } else {//结束日期的月份不等于开始日期的月份
                if (m < 0) {//结束日期的月份小于开始日期的月份，年份需减1
                    y = y - 1;
                }
                Date newdate1 = getNextNMonth(date1, y * 12 + 12);//计算一年之后的日期，与结束日期进行比较
                int compareVal = compare_date(getDateStr(newdate1, "yyyy-MM-dd"), datestr2);
                if (compareVal == 1) {//不足1年，按1计算
                    result = y + 1;
                } else {//超过半年或等于半年，按1计算
                    result = y + 1;
                }
            }
        }
        return result;
    }
    
    /**
     * 计算服务年限数，满一年按一年计算；不满一年，精确到日计算，保留三位小数。
     *
     * @param date1 开始日期yyyy-MM-dd
     * @param date2 结束日期yyyy-MM-dd
     * @return 服务年限
     * @throws Exception
     */
    public static double getServiceYears(String datestr1, String datestr2) throws Exception {
        double result = 0;
        Date date1 = parseDate(datestr1, "yyyy-MM-dd");
        Calendar date1Cal = Calendar.getInstance();
        date1Cal.setTime(date1);
        Date date2 = parseDate(datestr2, "yyyy-MM-dd");
        Calendar date2Cal = Calendar.getInstance();
        date2Cal.setTime(date2);
        int y = date2Cal.get(Calendar.YEAR) - date1Cal.get(Calendar.YEAR);
        int m = date2Cal.get(Calendar.MONTH) - date1Cal.get(Calendar.MONTH);
        int d = date2Cal.get(Calendar.DATE) - date1Cal.get(Calendar.DATE);
        if (compare_date(datestr2, datestr1) == 1) {//结束日期大于开始日期
            if (m < 0) {//结束日期的月份小于开始日期的月份
                y = y - 1;
            } else {//结束日期的月份不等于开始日期的月份
                if(d < 0) {
                	y = y - 1;
                }
            }
            Date newdate1 = getNextNMonth(date1, y * 12);
            int compareVal = compare_date(getDateStr(newdate1, "yyyy-MM-dd"), datestr2);
            if (compareVal == 0) {//整年
                result = y;
            } else {//非整年
            	int btnDays = daysBetween(newdate1, date2);
            	BigDecimal b1 = new BigDecimal(Double.toString(btnDays));
        		BigDecimal b2 = new BigDecimal(Double.toString(365));
        		double btnYears =  b1.divide(b2,3, BigDecimal.ROUND_HALF_UP).doubleValue();
                result = y + btnYears;
            }
        }
        return result;
    }

    /**
     * 获取工作日集合
     *
     * @param year  年
     * @param month 月
     * @return List<Date>工作日集合
     * @date 2017年8月23日 下午6:02:45
     */
    public static List<Date> getDates(int year, int month) {
        List<Date> dates = new ArrayList<Date>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        while (cal.get(Calendar.YEAR) == year &&
                cal.get(Calendar.MONTH) < month) {
            int day = cal.get(Calendar.DAY_OF_WEEK);

            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                dates.add((Date) cal.getTime().clone());
            }
            cal.add(Calendar.DATE, 1);
        }
        return dates;
    }

    /**
     * 获得指定日期的前一天
     *
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getSpecifiedDayBefore(String specifiedDay) {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     *
     * @param specifiedDay
     * @return
     */
    public static String getSpecifiedDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }



    public static String firstDayMonth() {
        Calendar cale = null;
        cale = Calendar.getInstance();
        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday;
        // 获取前月的第一天  
        cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());
        return firstday;
    }

    public static String lastDayMonth() {
        Calendar cale = null;
        cale = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String lastday;
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        lastday = format.format(cale.getTime());
        return lastday;
    }

    /**
     * 获取前一个月
     *
     * @param month
     * @return
     */
    public static String getBeforeMonth(String month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date monthDate;
        String mon = "";
        try {
            monthDate = format1.parse(month + "-01");
            Calendar c = Calendar.getInstance();
            c.setTime(monthDate);
            c.add(Calendar.MONTH, -1);
            Date m = c.getTime();
            mon = format.format(m);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mon;
    }
    /**
     * 获取后一个月
     *
     * @param month
     * @return
     */
    public static String getAfterMonth(String month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date monthDate;
        String mon = "";
        try {
            monthDate = format1.parse(month + "-01");
            Calendar c = Calendar.getInstance();
            c.setTime(monthDate);
            c.add(Calendar.MONTH, 1);
            Date m = c.getTime();
            mon = format.format(m);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return mon;
    }

    /**
     * 验证日期有效格式
     *
     * @param str
     * @return
     */
    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false.
            // 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
    /**
     * 验证日期有效格式
     *
     * @param str
     * @return
     */
    public static boolean isValidFormatDate(String str, String format_type) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat(format_type);
        try {
            // 设置lenient为false.
        	format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
    /**
     * 下月第1天
     */
    public static String getNextFirstDay(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 含日的日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return df.format(calendar.getTime());
    }
    /**
     * 下月最后一天
     */
    public static String getNextEndDay(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 含日的日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return df.format(calendar.getTime());
    }

    /** 
     *  获取两个日期相差的月数 
     * @param d1    较大的日期 
     * @param d2    较小的日期 
     * @return  如果d1>d2返回 月数差 否则返回0 
     */  
    public static int getMonthDiff(Date d1, String bdate) {
    	Date d2 = null;
    	int yearInterval = 0;
    	int monthInterval = 0;
    	try {
    		d2 = getDatetwo(bdate);
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(d1);  
            c2.setTime(d2);  
            if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;  
            int year1 = c1.get(Calendar.YEAR);
            int year2 = c2.get(Calendar.YEAR);
            int month1 = c1.get(Calendar.MONTH);
            int month2 = c2.get(Calendar.MONTH);
            int day1 = c1.get(Calendar.DAY_OF_MONTH);
            int day2 = c2.get(Calendar.DAY_OF_MONTH);
            // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30  
            yearInterval = year1 - year2;  
            // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数  
            if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;  
            // 获取月数差值  
            monthInterval =  (month1 + 12) - month2  ;  
            if(day1 < day2) monthInterval --;  
            monthInterval %= 12;  
           
		} catch (Exception e) {
			
		}
    	return yearInterval * 12 + monthInterval;  
    	
    } 
    /** 
     *  获取两个日期相差的月数 
     * @param d1    较大的日期 
     * @param d2    较小的日期 
     * @return  如果d1>d2返回 月数差 否则返回0 
     */  
    public static int getMonthDiff(String date1, String date2) {
    	int yearInterval = 0;
    	int monthInterval = 0;
    	try {
    		Date d1 = getDatetwo(date1);
    		Date d2 = getDatetwo(date2);
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(d1);  
            c2.setTime(d2);  
            if(c1.getTimeInMillis() < c2.getTimeInMillis()) return 0;  
            int year1 = c1.get(Calendar.YEAR);
            int year2 = c2.get(Calendar.YEAR);
            int month1 = c1.get(Calendar.MONTH);
            int month2 = c2.get(Calendar.MONTH);
            int day1 = c1.get(Calendar.DAY_OF_MONTH);
            int day2 = c2.get(Calendar.DAY_OF_MONTH);
            // 获取年的差值 假设 d1 = 2015-8-16  d2 = 2011-9-30  
            yearInterval = year1 - year2;  
            // 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数  
            if(month1 < month2 || month1 == month2 && day1 < day2) yearInterval --;  
            // 获取月数差值  
            monthInterval =  (month1 + 12) - month2  ;  
            if(day1 < day2) monthInterval --;  
            monthInterval %= 12;  
           
		} catch (Exception e) {
			
		}
    	return yearInterval * 12 + monthInterval;  
    	
    } 
    /**
     * 判断是否是闰年,是返回true
     * @return
     */
    public static boolean ifLeapYear(int year){
    	if(year%4==0&&year%100!=0||year%400==0){
    		return true;
    	}else{
    		return false;
    	}
    }
    public static String subMonth(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);  
        rightNow.add(Calendar.MONTH, -1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;  
    }

    /**
     * 获取任意时间下个月的最后一天
     * 描述:<描述函数实现的功能>.
     * @param repeatDate
     * @return
     */
    public static Date getNextNMaxMonthDate(Date repeatDate, Integer n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(repeatDate);
        calendar.add(Calendar.MONTH,  n);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    
    /**
     * i个月之前的月份
     * 格式 yyyy-MM 
     * @return
     */
    public static String preMonth(String date, int i) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, -i);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;  
    }
    //获取i月之后的月份
    public static String afterMonth(String date, int i) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, i);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;  
    }
    /**
     * i个年之前的月份
     * 格式 yyyy-MM 
     * @return
     */
    public static String preYear(String date, int i) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.YEAR, -i);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;  
    }
    /**
     * 指定日期上月最后1天
     * @throws ParseException
     */
    public static String preFirstDay(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 含日的日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(df.parse(date));
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return df.format(calendar.getTime());
    }
    
    
    /**
     *  计算俩个日期之间相差月数 
     * @throws ParseException
     */
    public static int getMonthSpace(String date1, String date2)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));
        
        int result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        int month = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }
    
    
    
    public static boolean isBeforeMonth(String beforeMonth, String currentMonth) throws ParseException {
    	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
          Date bm=sdf.parse(beforeMonth);
          Date cm=sdf.parse(currentMonth);
          Calendar cmCal= Calendar.getInstance();
          Calendar bmCal= Calendar.getInstance();
          cmCal.setTime(cm);
          bmCal.setTime(bm);
          boolean isSameYear = cmCal.get(Calendar.YEAR) == bmCal
                   .get(Calendar.YEAR);
           boolean isSameMonth = isSameYear
                   && cmCal.get(Calendar.MONTH) == bmCal.get(Calendar.MONTH);
        
        return isSameMonth;
    }

    /**
     * 英语日期转汉字日期
     * @param date
     * @return
     */
    public static String dateToString (String date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年M月d日");
        String date_new = null;
        try {
            date_new = dateFormat.format(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date_new;
    }

    public static String getHHMMSS(Date date) {
        if (date == null) {
            return "";
        }
        df.applyPattern("HH:mm:ss");
        return df.format(date);
    }

}
