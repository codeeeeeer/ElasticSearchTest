package com.lewjay.elapp.utils;

import com.lewjay.elapp.constants.ELConstants;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * 〈〉
 *
 * @author liujie
 * @create 2019/10/14 10:36
 */
public class MappingUtils {
    public static <T> T str2Object(Class<T> tClass, String source, String splitStr) throws IllegalAccessException, InstantiationException, ParseException {
        Assert.notNull(source, "source can not be null");
        String[] contents = split(source);

        Field[] declaredFields = tClass.getDeclaredFields();
        Assert.isTrue(contents.length == declaredFields.length, "the size of fields is not equal to the contents");
        T result = tClass.newInstance();
        for (int i = 0; i < declaredFields.length; i ++) {
            Field field = declaredFields[i];
            String content = ELUtils.trimQuotes(contents[i]);
            field.setAccessible(true);
            Class declaringClass = field.getType();
            if (declaringClass.isAssignableFrom(String.class) ){
                field.set(result, content);
            }else if (declaringClass.isAssignableFrom(Integer.class)){
                field.set(result, StringUtils.isEmpty(content) ? 0 : Integer.valueOf(content));
            }else if (declaringClass.isAssignableFrom(Date.class)){
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                if (StringUtils.isEmpty(content)){
                    field.set(result, new Date());
                }else{
                    Date parse = dateFormat.parse(content);
                    field.set(result, parse);
                }
            }
        }
        return result;
    }

    public static String[] split(String source){
        if (StringUtils.isEmpty(source)) {
            return new String[0];
        }
        String[] contents = source.split(ELConstants.SPLIT_STR);
        ArrayList<String> resultContainer = new ArrayList<>();
        for (int i = 0; i < contents.length; i++) {
            int countMatches = StringUtils.countMatches(contents[i], "\"");
            if ((countMatches & 1) == 1 ){
                StringBuilder currentContent = new StringBuilder(contents[i]);
                while (((countMatches += StringUtils.countMatches(contents[++i], "\"")) & 1) == 1){
                    currentContent.append(ELConstants.SPLIT_STR).append(contents[i]);
                }
                currentContent.append(ELConstants.SPLIT_STR).append(contents[i]);
                resultContainer.add(currentContent.toString());
            }else{
                resultContainer.add(contents[i]);
            }
        }
        return resultContainer.toArray(new String[resultContainer.size()]);
    }
}
