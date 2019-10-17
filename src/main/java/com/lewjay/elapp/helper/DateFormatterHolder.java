package com.lewjay.elapp.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/15 23:13
 */
public enum  DateFormatterHolder {
    $1("yyyy-MM-dd");
    private final String pattern;
    private final ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<>();

    DateFormatterHolder(String pattern) {
        this.pattern = pattern;
    }

    public DateFormat getDateFormat(){
        DateFormat dateFormat = dateFormatThreadLocal.get();
        if (dateFormat == null){
            dateFormat = new SimpleDateFormat(this.pattern);
            dateFormatThreadLocal.set(dateFormat);
        }
        return dateFormat;
    }

    public Date parse(String source) throws ParseException {
        return getDateFormat().parse(source);
    }

    public String format(Date sourceDate){
        return getDateFormat().format(sourceDate);
    }
}
